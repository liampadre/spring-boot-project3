package com.example.projectweb1.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Projectweb1Application implements CommandLineRunner {

    Logger log = LoggerFactory.getLogger(Projectweb1Application.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(Projectweb1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        String pwd1 = "12345";
//        String pwd2 = "54321";
//
//        String bCryptPassword1 = passwordEncoder.encode(pwd1);
//        log.info(bCryptPassword1);
//        String bCryptPassword2 = passwordEncoder.encode(pwd2);
//        log.info(bCryptPassword2);
    }
}
