package com.joseph.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joseph.entity.Product;
import com.joseph.service.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public String listclients(Model Model) {
        List<Product> Products = productService.getProducts();
        Model.addAttribute("products", Products);
        return "listproducts";
    }

    @GetMapping("/add")
    public String addproduct(Model Model) {
        List<Product> Products = productService.getProducts();
        Model.addAttribute("products", Products);
        return "addproduct";
    }

    @PostMapping("/save")
    public String saveclient(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/product/list";
    }

    @GetMapping("/updatepage")
    public String showFormForUpdate(@RequestParam("id") Long id, Model theModel) throws NullPointerException {
        Product product = productService.getProduct(id);
        theModel.addAttribute("product", product);
        return "updateproduct";
    }

    @PostMapping("/update")
    public String showFormForUpdate(@ModelAttribute("product") Product product) {
        System.out.println(product.getIdproduct());
        productService.saveProduct(product);
        return "redirect:/product/list";
    }

    @GetMapping("/delete")
    public String deleteclient(@RequestParam("id") Long id) throws NullPointerException {
        productService.deleteProduct(id);
        return "redirect:/product/list";
    }
}
