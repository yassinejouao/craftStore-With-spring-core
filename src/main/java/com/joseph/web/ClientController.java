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

import com.joseph.entity.Client;
import com.joseph.service.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/list")
    public String listclients(Model Model) {
        List<Client> Clients = clientService.getClients();
        Model.addAttribute("clients", Clients);
        return "listclients";
    }

    @GetMapping("/add")
    public String addclient(Model Model) {
        Client Client = new Client();
        Model.addAttribute("client", Client);
        return "addclient";
    }

    @PostMapping("/save")
    public String saveclient(@ModelAttribute("client") Client Client) {
        clientService.saveClient(Client);
        return "redirect:/client/list";
    }

    @GetMapping("/delete")
    public String deleteclient(@RequestParam("id") int idclient) throws NullPointerException {
        clientService.deleteClient(idclient);
        return "redirect:/client/list";
    }
}