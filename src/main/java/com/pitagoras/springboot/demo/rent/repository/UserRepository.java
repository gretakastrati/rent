package com.pitagoras.springboot.demo.rent.repository;

import com.pitagoras.springboot.demo.rent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);


}
