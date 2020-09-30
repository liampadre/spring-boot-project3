package com.example.projectweb1.demo.controller;

import com.example.projectweb1.demo.dao.impl.ClientDao;
import com.example.projectweb1.demo.model.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
// its newer than @Secured, we can use SpEL (Spring Expresion Language)
@PreAuthorize("hasRole('ROLE_CLIENT') and hasRole('ROLE_ADMIN')")
@RequestMapping("/clients")
public class ClientController {

    Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientDao dao;

    @GetMapping(value = {"", "/"})
    public String showCurrentList(Model model, Authentication authentication) {
        List<Client> clients = dao.findByStatus("ENABLED");
        model.addAttribute("titular", "Florentino Perez");
        model.addAttribute("description", "Current client list");
        model.addAttribute("clients", clients);
        model.addAttribute("capturedUser", authentication.getName());
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            // Authority is the string with the role for example ROLE_ADMIN, ROLE_USER.
            log.info("The role is: ".concat(authority.getAuthority()));
        }
        // Also we can use SecurityContextHolderAwareRequestWrapper.isUserInRole(...) for validate roles, is better
        // or the native way using HttpServletRequest.isUserInRole(...)
        model.addAttribute("userRoles", authorities);
        return "clients";
    }

    @GetMapping(value = "/all")
    public String showFullList(Model model) {
        List<Client> clients = dao.findAll();
        model.addAttribute("titular", "Florentino Perez");
        model.addAttribute("description", "All client list");
        model.addAttribute("clients", clients);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("capturedUser", authentication.getName() + "-all");

        return "clients";
    }
}
