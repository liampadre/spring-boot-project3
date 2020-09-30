package com.example.projectweb1.demo.controller;

import com.example.projectweb1.demo.dao.impl.EmployeeDao;
import com.example.projectweb1.demo.model.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeDao dao;

    @GetMapping(value = {"", "/"})
    public String showFullList(Model model) {
        List<Employee> employees = dao.findAll();
        model.addAttribute("titular", "Pedro Sanchez");
        model.addAttribute("description", "All employees list");
        model.addAttribute("employees", employees);

        return "employees";
    }

    @GetMapping(value = "/active")
    public String showFiredList(Model model) {
        List<Employee> employees = dao.findByStatus("ACTIVE");
        model.addAttribute("titular", "Pedro Sanchez");
        model.addAttribute("description", "Fired employees list");
        model.addAttribute("employees", employees);

        return "employees";
    }
}
