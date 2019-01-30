package com.example.springSecurity.controllers;


import com.example.springSecurity.models.User;
import com.example.springSecurity.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/create")
public class UserController {


    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/reg")
    @PostMapping
    public void createUser(@Valid @RequestBody User user) throws Exception {
        String username = "admin";
        //int active  =  Integer.parseInt(request.getParameter("active"));
        user.setPassword(new BCryptPasswordEncoder(11).encode(user.getPassword()));

        System.out.println(user.getPassword());

        //String password = request.getParameter("password");
        User u = userRepository.save(user);

        if (u != null) {
            throw new Exception();
        }

    }
}
