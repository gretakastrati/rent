package com.pitagoras.springboot.demo.rent.repository;

import com.pitagoras.springboot.demo.rent.entity.ContactUsMessage;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactUsMessageRepository extends JpaRepository<ContactUsMessage, Long> {

}


