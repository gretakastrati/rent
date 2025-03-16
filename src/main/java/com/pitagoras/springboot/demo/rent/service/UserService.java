package com.pitagoras.springboot.demo.rent.service;

import com.pitagoras.springboot.demo.rent.entity.User;

public interface UserService {

    User save (User user);

    User findById(int id);
}
