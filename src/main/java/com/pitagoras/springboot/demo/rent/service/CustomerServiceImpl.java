package com.pitagoras.springboot.demo.rent.service;

import com.pitagoras.springboot.demo.rent.entity.Customer;
import com.pitagoras.springboot.demo.rent.repository.CustomerRepository;
import com.pitagoras.springboot.demo.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Customer save(Customer customer) {
//        Customer c = new Customer();
//        c.setPersonalNumber(customer.getPersonalNumber());
//        c.setPhoneNumber(customer.getPhoneNumber());
//        User u = new User();
//        u.setName(customer.getUserName());
//        u.setEmail(customer.getEmail());
//
//        c.setUser(u);
//        this.userRepository.save(u);
//        return this.customerRepository.save(c);
        return this.customerRepository.save(customer);

    }
     @Override
    public Customer findCustomerById(int id) {
         Optional<Customer> customer = this.customerRepository.findById(id);

         if(!customer.isPresent()) {
             throw new RuntimeException("Customer not found");

         }
         return customer.get();
     }
}

