package com.pitagoras.springboot.demo.rent.rest;



import com.pitagoras.springboot.demo.rent.entity.Order;
import com.pitagoras.springboot.demo.rent.repository.OrderRepository;
import com.pitagoras.springboot.demo.rent.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private  OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order){
        System.out.println(order);
        return this.orderService.save(order);
    }
    @GetMapping("/{orderId}")
    public Order findById(@PathVariable long orderId){
        return this.orderService.findById(orderId);
    }
    @PutMapping("/{id}")
    public Order updateOrder(@RequestBody Order order, @PathVariable long id) {
        Order toUpdateOrder = this.orderService.findById(id);
        if(toUpdateOrder == null){
            throw new RuntimeException("Order with id "+ id + "not found to update");
        }
        order.setId(id);
        return this.orderService.updateOrder(order);
    }
    @GetMapping("/list")
    public List<Order> findAll(){
        return this.orderService.findAll();
    }

    @DeleteMapping("/{id}")
    public boolean deleleOrder(@PathVariable long id){
        return orderService.deleteById(id);
    }
    @GetMapping("/list/{customerId}")
    public List<Order> findByCustomerId(@PathVariable Integer customerId){
        return this.orderService.findByCustomerId(customerId);
    }

}