package com.example.projectweb1.demo.dao;

import com.example.projectweb1.demo.model.entity.Employee;

import java.util.List;

public interface IEmployeeDao {

    List<Employee> findAll();

    Employee findById(Long id);

    List<Employee> findByStatus(String status);

}
