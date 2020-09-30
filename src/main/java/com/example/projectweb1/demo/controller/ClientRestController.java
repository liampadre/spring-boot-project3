package com.example.projectweb1.demo.controller;

import com.example.projectweb1.demo.dao.impl.ClientDao;
import com.example.projectweb1.demo.model.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_CLIENT') and hasRole('ROLE_USER')")
@RequestMapping("/api/clients")
public class ClientRestController {

    @Autowired
    private ClientDao dao;

    @GetMapping(value = "/all")
    public List<Client> showFullList() {
        List<Client> clients = dao.findAll();
        return clients;
    }
}
