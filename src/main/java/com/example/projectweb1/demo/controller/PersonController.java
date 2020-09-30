package com.example.projectweb1.demo.controller;

import com.example.projectweb1.demo.dao.impl.ClientDao;
import com.example.projectweb1.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/persons")
@SessionAttributes("person")
public class PersonController {

    @Autowired
    private ClientDao dao;

    @GetMapping(value = {"", "/", "/test"})
    public String showHello0() {
        return "hello";
    }

    @GetMapping(value = "/test1")
    public String showHello1(Model model) {
        Person person = new Person();
        person.setName("RODRIGO");
        person.setLastname("VILLEGAS");
        person.setAge(25);
        model.addAttribute("myperson", person);

        return "persons";
    }

    @GetMapping(value = "/test2")
    public String showHello2(Model model, @ModelAttribute("person") Person person) {
        /* Testing annotation SessionAttributes with ModelAttribute - Paso 1 */
        if (person != null) {
            person.setAge(35);
            model.addAttribute("myperson", person);
        } else {
            Person localPerson = new Person();
            localPerson.setName("JOSE");
            localPerson.setLastname("VILLA");
            model.addAttribute("myperson", localPerson);
        }

        return "persons";
    }

    @GetMapping(value = "/test3")
    public String showHello3(Model model, @ModelAttribute("person") Person person, SessionStatus status) {
        /* Testing annotation SessionAttributes with ModelAttribute - Paso 1 */
        status.setComplete();
        /* Although invoke setComplete () method, object person keeps current value until current request ends */
        if (person != null) {
            person.setLastname("VERA");
            model.addAttribute("myperson", person);
        } else {
            Person localPerson = new Person();
            localPerson.setName("PABLO");
            localPerson.setLastname("LUNA");
            model.addAttribute("myperson", localPerson);
        }

        return "persons";
    }

    @GetMapping(value = "/test4")
    public String showHello4(Model model, @ModelAttribute("person") Person person) {
        /* Testing annotation SessionAttributes with ModelAttribute - Paso 3 */
        if (person != null) {
            model.addAttribute("myperson", person);
        } else {
            Person localPerson = new Person();
            localPerson.setName("RAQUEL");
            localPerson.setLastname("PEREZ");
            model.addAttribute("myperson", localPerson);
        }

        return "persons";
    }

    @ModelAttribute("person")
    public Person person() {
        return new Person("JUAN", "LOPEZ", 100);
    }
}
