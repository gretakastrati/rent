package com.pitagoras.springboot.demo.rent.service;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;
import com.pitagoras.springboot.demo.rent.dto.ContactUsMessageRequestDto;
import com.pitagoras.springboot.demo.rent.dto.ContactUsMessageResponseDto;
import com.pitagoras.springboot.demo.rent.entity.ContactUsMessage;
import com.pitagoras.springboot.demo.rent.repository.ContactUsMessageRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactUsMessageServiceImpl implements ContactUsMessageService {

    private ContactUsMessageRepository contactUsMessageRepository;
    private MailjetClient client;

    @Autowired
    public ContactUsMessageServiceImpl(ContactUsMessageRepository contactUsMessageRepository) {

        this.contactUsMessageRepository = contactUsMessageRepository;
        this.client = new MailjetClient("026b5d3b31d90ae438df911791a26274", "c3c3d10d90caa01788f34d0d3e6a387e");
    }

    @Override
    public ContactUsMessageResponseDto createContactUsMessage(ContactUsMessageRequestDto contactUsMessageRequestDto) {
        ContactUsMessage message = new ContactUsMessage();
        message.setName(contactUsMessageRequestDto.getName());
        message.setEmail(contactUsMessageRequestDto.getEmail());
        message.setSubject(contactUsMessageRequestDto.getSubject());
        message.setMesssage(contactUsMessageRequestDto.getMessage());

        ContactUsMessage contactUsMessageCreated = this.contactUsMessageRepository.save(message);
        ContactUsMessageResponseDto responseDto = new ContactUsMessageResponseDto();
        responseDto.setId(contactUsMessageCreated.getId());
        responseDto.setEmail(contactUsMessageRequestDto.getEmail());
        responseDto.setSubject(contactUsMessageCreated.getSubject());
        responseDto.setMessage(contactUsMessageRequestDto.getMessage());
        responseDto.setCreatedAt(contactUsMessageCreated.getCreatedAt());


        MailjetRequest request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put("From", new JSONObject()
                                        .put("Email", "ardianbsyla@gmail.com")
                                        .put("Name", "Your App Name"))
                                .put("To", new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", "kastratigreta1@gmail.com") // Your destination email
                                                .put("Name", "Admin")))
                                .put("Subject", contactUsMessageRequestDto.getSubject())
                                .put("TextPart", contactUsMessageRequestDto.getMessage())
                                .put("HTMLPart", String.format("<h4>New Contact Message</h4><p><b>Name:</b> %s<br><b>Email:</b> %s<br><b>Message:</b><br>%s</p>", contactUsMessageRequestDto.getName(), contactUsMessageRequestDto.getEmail(), contactUsMessageRequestDto.getMessage()))
                        ));

        try{
            MailjetResponse response = client.post(request);

        } catch (MailjetException e) {
            throw new RuntimeException(e);
        }



        return responseDto;
    }

    @Override
    public ContactUsMessageResponseDto findById(Long id) {


        ContactUsMessageResponseDto cumr = new ContactUsMessageResponseDto();
        Optional<ContactUsMessage> message = this.contactUsMessageRepository.findById(id);
        if (message.isPresent()) {
            cumr.setId(message.get().getId());
            cumr.setName(message.get().getName());
            cumr.setEmail(message.get().getEmail());
            cumr.setCreatedAt(message.get().getCreatedAt());
            cumr.setSubject(message.get().getSubject());
            cumr.setMessage(message.get().getMesssage());

            return cumr;
        }
        return null;
    }

    @Override
    public List<ContactUsMessageResponseDto> findAll() {
        return List.of();
    }
}
