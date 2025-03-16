package com.pitagoras.springboot.demo.rent.service;

import com.pitagoras.springboot.demo.rent.entity.Customer;

public interface CustomerService {

    Customer save(Customer customer);
    Customer findCustomerById(int id);
}
