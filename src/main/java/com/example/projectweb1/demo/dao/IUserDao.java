package com.example.projectweb1.demo.dao;

import com.example.projectweb1.demo.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<User, Long> {

    public User findByUsername(String username);
}
