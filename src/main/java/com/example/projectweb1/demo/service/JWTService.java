package com.example.projectweb1.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.util.Collection;

public interface JWTService {

    String create(Authentication auth) throws JsonProcessingException;

    boolean validate(String token);

    Claims getClaims(String claims);

    String getUsername(String token);

    Collection<? extends GrantedAuthority> getRoles(String token) throws IOException;

    String resolve(String token);

}
