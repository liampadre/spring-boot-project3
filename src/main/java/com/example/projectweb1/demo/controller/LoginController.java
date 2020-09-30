package com.example.projectweb1.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class LoginController {

    Logger log = LoggerFactory.getLogger(LoginController.class);

//    @GetMapping("/")
//    public String hello(RedirectAttributes flash, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        flash.addFlashAttribute("success", "Bien logado chaval!");
//        return "redirect:/clients";
//    }

    @GetMapping("/")
    public String hello(RedirectAttributes flash, HttpServletRequest request) {
//        return "redirect:/api/clients/all";
        return "hello";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required=false) String error,
                        @RequestParam(required=false) String success,
                        Model model, Principal principal, RedirectAttributes flash) {

        log.info("We are here!");

        if (!ObjectUtils.isEmpty(principal)){
            flash.addFlashAttribute("message", "You are logged!");
            return "redirect:/persons";
        }

        if (error != null) {
            model.addAttribute("error", "An unexpected error in login process happened!");
        }

        if (success != null) {
            model.addAttribute("success", "Closed session successfully!!");
        }

        return "login";
    }

}
