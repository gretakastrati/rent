package com.pitagoras.springboot.demo.rent.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralControler {
    @Value("${car.name}")
    private String carName;

    @Value("${motor.type}")
    private String motorType;

    @GetMapping("/")
    public String sayHello(){
        return "Hello world!,my car name is "+ this.carName + ",and motor type is "+ motorType;
    }

    @GetMapping("/about")
    public String tellAboutApp(){
        return "This is an app we are creating for learning purposes.!";
    }

    @GetMapping("/devtools")
    public String testing(){
        return "This should work";
    }
    @GetMapping("/test")
    public String test(){
        return "Test 1 ksdjksd";
    }
}