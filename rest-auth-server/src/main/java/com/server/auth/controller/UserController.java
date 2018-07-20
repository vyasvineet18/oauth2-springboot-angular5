package com.server.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.auth.dao.RoleDao;
import com.server.auth.dao.UserDao;

@RestController
@RequestMapping("/oauth/api")
public class UserController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /*@GetMapping("/public/")
    public Page<UserEntity> user(
            Pageable pageable,
            @RequestParam String username
    ) {
        return  userDao.findByUserName(username, pageable);
    }*/

    

    
}