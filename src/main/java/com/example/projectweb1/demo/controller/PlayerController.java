package com.example.projectweb1.demo.controller;

import com.example.projectweb1.demo.model.Person;
import com.example.projectweb1.demo.model.Player;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Secured({"ROLE_USER", "ROLE_CLIENT"})
@RequestMapping("/players")
public class PlayerController {

    @GetMapping(value = "/test0")
    public @ResponseBody Player test0() {
        Player player = new Player();
        player.setName("ANDY");
        player.setLastname("MURRAY");
        player.setAge(35);
        player.setSport("TENNIS");
        return player;
    }

    @GetMapping(value = "/test1")
    public String test1(RedirectAttributes flash, Model model) {
        flash.addFlashAttribute("player1", "SVITOLINA");
        model.addAttribute("player2", "MUGURUZA");
        return "players"; // If uncomment this code line only shows the value of player2 because do not execute redirect, player1 value lost
//        return "redirect:test3"; // If uncomment this code line only shows the value of player 1 and the values that could be add in /test3 step, player2 value lost
        //https://docs.spring.io/spring/docs/5.0.8.RELEASE/javadoc-api/index.html?org/springframework/web/servlet/mvc/support/RedirectAttributes.html
        //A RedirectAttributes model is empty when the method is called and is never used unless the method returns a redirect view name or a RedirectView.
    }

    @GetMapping(value = "/test2")
    public String test2(RedirectAttributes flash) {
        flash.addFlashAttribute("player1", "NOVAK");
        flash.addAttribute("player2", "ROGER"); // Add as a request param
        return "redirect:test3";
    }

    @GetMapping(value = "/test3")
    public String test3(Model model) {
        model.addAttribute("player3", "ARANTXA");
        return "players";
    }

    @GetMapping(value = "/test4")
    public String test4(Model model, @SessionAttribute("person") Person person) {
        model.addAttribute("myperson", person);
        return "persons";
    }

    @GetMapping(value = "/test5")
    public String test5(Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("myperson", person);
        return "persons";
    }
}
