package com.example.projectweb1.demo.service;

import com.example.projectweb1.demo.dao.IUserDao;
import com.example.projectweb1.demo.model.entity.Role;
import com.example.projectweb1.demo.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalUserDetailsService implements UserDetailsService {

    Logger log = LoggerFactory.getLogger(LocalUserDetailsService.class);

    @Autowired
    private IUserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByUsername(s);

        if (ObjectUtils.isEmpty(user)) {
            log.error("ERROR LOGIN: User does not exist.");
            throw new UsernameNotFoundException("Username not found: " + s);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            log.info("Role: " + role.getAuthority());
            roles.add(new SimpleGrantedAuthority(role.getAuthority()));
        }

        if (CollectionUtils.isEmpty(roles)) {
            log.error("ERROR LOGIN: User does have any role.");
            throw new UsernameNotFoundException("Username without roles: " + s);
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.getEnabled(), true, true, true, roles);
    }
}
