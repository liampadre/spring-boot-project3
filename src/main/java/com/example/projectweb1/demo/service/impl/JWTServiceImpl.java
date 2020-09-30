package com.example.projectweb1.demo.service.impl;

import com.example.projectweb1.demo.config.SimpleGrantedAuthorityMixin;
import com.example.projectweb1.demo.service.JWTService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Component
public class JWTServiceImpl implements JWTService {

    private static final String SECRET_KEY = "mysecretkey";

    @Override
    public String create(Authentication auth) throws JsonProcessingException {
        String username = ((User) auth.getPrincipal()).getUsername();
        Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
        Claims claims = Jwts.claims();
        claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (3600000 * 4)))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())
                .compact();

        return token;
    }

    @Override
    public boolean validate(String authToken) {
        try {
            getClaims(authToken);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    @Override
    public Claims getClaims(String authToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(resolve(authToken))
                .getBody();

        return claims;
    }

    @Override
    public String getUsername(String authToken) {
        return getClaims(authToken).getSubject();
    }

    @Override
    public Collection<? extends GrantedAuthority> getRoles(String authToken) throws IOException {
        Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                (new ObjectMapper())
                        .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
                        .readValue(getClaims(authToken).get("authorities").toString().getBytes(),
                                SimpleGrantedAuthority[].class));

        return authorities;
    }

    @Override
    public String resolve(String authToken) {
        if (authToken.toLowerCase().startsWith("bearer ")) {
            return authToken.split(" ")[1];
        } else {
            return null;
        }
    }
}
