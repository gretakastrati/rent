
package com.pitagoras.springboot.demo.rent.rest;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(String message) {
        super(message);
    }
}