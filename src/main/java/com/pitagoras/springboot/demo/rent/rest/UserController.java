package com.pitagoras.springboot.demo.rent.rest;


import com.pitagoras.springboot.demo.rent.entity.User;
import com.pitagoras.springboot.demo.rent.repository.CustomerRepository;
import com.pitagoras.springboot.demo.rent.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final CustomerRepository customerRepository;

    public UserController(UserService userService, CustomerRepository customerRepository) {
        this.userService = userService;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return this.userService.findById(id);
    }


}

