package com.joseph.web;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joseph.Model.rapportData;
import com.joseph.Model.receivedData;
import com.joseph.Model.receivedData.orderData;
import com.joseph.entity.Client;
import com.joseph.entity.Order;
import com.joseph.entity.OrderItem;
import com.joseph.entity.Product;
import com.joseph.entity.Status;
import com.joseph.service.*;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/list")
    public String ListOrders(Model Model) {
        List<Order> Orders = orderService.getOrders();
        Status[] statusValues = Status.values();
        Model.addAttribute("orders", Orders);
        Model.addAttribute("statusValues", statusValues);
        return "listorders";
    }

    @GetMapping("/add")
    public String addorder(Model Model) {
        List<Client> Clients = clientService.getClients();
        List<Product> Products = productService.getProducts();
        Model.addAttribute("clients", Clients);
        Model.addAttribute("products", Products);
        return "addorder";
    }

    @GetMapping("/delete")
    public String deleteorder(@RequestParam("id") long id) throws NullPointerException {
        orderService.deleteOrder(id);
        return "redirect:/order/list";
    }

    // @RequestParam LocalDate starDate, @RequestParam LocalDate endDate,
    @GetMapping("/rapport")
    public String showrapport(Model Model) {
        return "rapport";
    }

    @PostMapping("/daterapport")
    public String showrapportdate(@RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr,
            Model model) {
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        List<rapportData> r = orderService.getTotalOrdersBetweenDates(startDate, endDate);
        for (rapportData rr : r) {
            System.out.println("here :");
            System.out.println(rr.getDate());
            System.out.println(rr.getTotal());
        }
        model.addAttribute("rapport", r);
        return "rapportwithdate";
    }

    @PostMapping("/updatestatus")
    public String updatestatus(@RequestParam("orderId") Long idorder, @RequestParam("status") Status status) {
        System.err.println(idorder);
        System.err.println(status);
        Order o = orderService.getOrder(idorder);
        o.setStatusorder(status);
        orderService.saveOrder(o);
        return "redirect:/order/list";
    }

    @PostMapping("/save")
    public String saveOrder(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder requestData = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestData.append(line);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        receivedData rd = objectMapper.readValue(requestData.toString(), receivedData.class);
        int idClient = Integer.parseInt(rd.getIdClient());
        Double totalorder = 0.0;
        List<orderData> orderData = rd.getOrder();
        Client c = new Client();
        c.setIdclient(idClient);
        Order o = new Order();
        o.setClient(c);
        o.setTotalorder(totalorder);
        orderService.saveOrder(o);
        for (orderData od : orderData) {
            Product p = productService.geProductsByName(od.getProductName());
            Double totalProduct = p.getPriceproduct() * Integer.parseInt(od.getQuantity());
            Integer quantity = 1 * Integer.parseInt(od.getQuantity());
            OrderItem oi = new OrderItem();
            oi.setOrder(o);
            oi.setProduct(p);
            oi.setQuantity(quantity);
            orderItemService.saveOrderItem(oi);
            totalorder += totalProduct;
            p.setStockproduct(p.getStockproduct() - quantity);
            productService.saveProduct(p);

        }
        o.setTotalorder(totalorder);
        orderService.saveOrder(o);
        return "addorder";
    }

    @PostMapping("/exportToExcel")
    public ResponseEntity<byte[]> exportToExcel(@RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) throws IOException {
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        List<rapportData> orderSummaries = orderService.getTotalOrdersBetweenDates(startDate, endDate);

        byte[] excelContent = createExcel(orderSummaries);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "orders.xlsx");
        headers.setContentLength(excelContent.length);

        return new ResponseEntity<>(excelContent, headers, org.springframework.http.HttpStatus.OK);
    }

    private byte[] createExcel(List<rapportData> orderSummaries) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Order Summary");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Date");
            headerRow.createCell(1).setCellValue("Total Orders");

            // Populate data rows
            int rowNum = 1;
            for (rapportData summary : orderSummaries) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(summary.getDate().toString());
                row.createCell(1).setCellValue(summary.getTotal());
            }

            workbook.write(out);
            return out.toByteArray();
        }
    }
}
