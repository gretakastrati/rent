package com.pitagoras.springboot.demo.rent.service;

import com.pitagoras.springboot.demo.rent.entity.Car;
import com.pitagoras.springboot.demo.rent.entity.Customer;
import com.pitagoras.springboot.demo.rent.entity.Order;
import com.pitagoras.springboot.demo.rent.entity.User;
import com.pitagoras.springboot.demo.rent.repository.CarRepository;
import com.pitagoras.springboot.demo.rent.repository.CustomerRepository;
import com.pitagoras.springboot.demo.rent.repository.OrderRepository;
import com.pitagoras.springboot.demo.rent.rest.CarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, CarRepository carRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Order save(Order order) {
        Order o = new Order();
        o.setRentalStartDate(order.getRentalStartDate());
        o.setRentalEndDate(order.getRentalEndDate());
        o.setTotalPrice(order.getTotalPrice());
        o.setStatus(order.getStatus());

        Optional<Customer> c = this.customerRepository.findById(order.getCustomerId());

        if (!c.isPresent()) {
            throw new CarNotFoundException("Customer with id " + order.getCustomerId() + " not found.");
        }
        o.setCustomer(c.get());

        Optional<Car> ca = this.carRepository.findById(order.getCarId());

        if (!ca.isPresent()) {
            throw new CarNotFoundException("Car with id " + order.getCarId() + " not found.");
        }

        o.setCar(ca.get());
        return this.orderRepository.save(o);
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> order = this.orderRepository.findById(id);

        if (!order.isPresent()) {
            throw new CarNotFoundException("Car with id " + id + " not found.");
        }
        return order.get();
    }
    @Override
    public Order updateOrder(Order theOrder) {
        Optional<Order> toUpdatedOrder = this.orderRepository.findById(theOrder.getId());
        if (toUpdatedOrder == null) {
            throw new CarNotFoundException("Order with id " + theOrder + " not found to update");
        }

        return this.orderRepository.save(theOrder);

    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Order> order = this.orderRepository.findById(id);
        if (!order.isPresent()) {
            throw new CarNotFoundException("Order with id " + id + " was not found");
        }
        this.orderRepository.deleteById(id);
        return true;

    }

    @Override
    public List<Order> findByCustomerId(Integer customerId) {
        return this.orderRepository.findByCustomerId(customerId);
    }
    public boolean isCarAvailable(int carId, LocalDate startDate, LocalDate endDate) {
        List<Order> overlappingOrders = orderRepository.findOverlappingOrders(carId, startDate, endDate);
        return overlappingOrders.isEmpty();
    }

}