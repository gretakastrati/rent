package com.pitagoras.springboot.demo.rent.rest;

import com.pitagoras.springboot.demo.rent.dto.ContactUsMessageRequestDto;
import com.pitagoras.springboot.demo.rent.dto.ContactUsMessageResponseDto;
import com.pitagoras.springboot.demo.rent.service.ContactUsMessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact-us")
public class ContactUsMessageController {

    private ContactUsMessageService contactUsMessageService;

    public ContactUsMessageController(ContactUsMessageService contactUsMessageService) {
        this.contactUsMessageService = contactUsMessageService;
    }

    @PostMapping
    public ContactUsMessageResponseDto createContactUsMessage(@RequestBody ContactUsMessageRequestDto contactUsMessageRequestDto) {
        return this.contactUsMessageService.createContactUsMessage(contactUsMessageRequestDto);
    }

}
