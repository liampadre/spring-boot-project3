package com.example.projectweb1.demo.dao;

import com.example.projectweb1.demo.model.entity.Client;

import java.util.List;

public interface IClientDao {

    List<Client> findAll();

    Client findById(Long id);

    List<Client> findByStatus(String status);

}
