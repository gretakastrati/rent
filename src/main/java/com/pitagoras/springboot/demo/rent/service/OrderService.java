package com.pitagoras.springboot.demo.rent.service;

import com.pitagoras.springboot.demo.rent.entity.Car;
import com.pitagoras.springboot.demo.rent.entity.Order;

import java.util.List;

public interface OrderService {

    Order save(Order order );

    Order findById(Long id );

    Order updateOrder(Order theOrder);

    List<Order> findAll();

    boolean deleteById(Long id);

    List<Order> findByCustomerId(Integer customerId);
}