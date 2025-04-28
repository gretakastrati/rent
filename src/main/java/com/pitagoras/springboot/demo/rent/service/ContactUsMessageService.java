package com.pitagoras.springboot.demo.rent.service;

import com.pitagoras.springboot.demo.rent.dto.ContactUsMessageRequestDto;
import com.pitagoras.springboot.demo.rent.dto.ContactUsMessageResponseDto;


import java.util.List;

public interface ContactUsMessageService {

    ContactUsMessageResponseDto createContactUsMessage(ContactUsMessageRequestDto contactUsMessageRequestDto);

    ContactUsMessageResponseDto findById(Long id);


    List<ContactUsMessageResponseDto> findAll();


}

