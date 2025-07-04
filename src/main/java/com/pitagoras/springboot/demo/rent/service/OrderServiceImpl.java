package com.pitagoras.springboot.demo.rent.service;

import com.pitagoras.springboot.demo.rent.dto.BookingRequestDto;
import com.pitagoras.springboot.demo.rent.entity.Car;
import com.pitagoras.springboot.demo.rent.entity.Customer;
import com.pitagoras.springboot.demo.rent.entity.Order;
import com.pitagoras.springboot.demo.rent.entity.User;
import com.pitagoras.springboot.demo.rent.repository.CarRepository;
import com.pitagoras.springboot.demo.rent.repository.CustomerRepository;
import com.pitagoras.springboot.demo.rent.repository.OrderRepository;
import com.pitagoras.springboot.demo.rent.repository.UserRepository;
import com.pitagoras.springboot.demo.rent.rest.CarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, CarRepository carRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Order save(BookingRequestDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setName(dto.getFirstName() + " " + dto.getLastName());
                    newUser.setEmail(dto.getEmail());
                    newUser.setUsername(dto.getEmail());
                    newUser.setPassword("default");
                    newUser.setEnabled(true);
                    newUser.setCreatedAt(LocalDateTime.now());
                    newUser.setUpdatedAt(LocalDateTime.now());
                    return userRepository.save(newUser);


                });

        Customer customer = customerRepository.findByUser(user)
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    newCustomer.setUser(user);
                    newCustomer.setPhoneNumber(dto.getPhoneNumber());
                    newCustomer.setCreatedAt(LocalDateTime.now());
                    newCustomer.setUpdatedAt(LocalDateTime.now());
                    return customerRepository.save(newCustomer);

                });

        Car car = carRepository.findById((long) dto.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found with id " + dto.getCarId()));

        Order order = new Order();
        order.setCustomer(customer);
        order.setCar(car);
        order.setRentalStartDate(dto.getRentalStartDate());
        order.setRentalEndDate(dto.getRentalEndDate());
        order.setPickupLocation(dto.getPickupLocation());
        order.setDropLocation(dto.getDropLocation());
        order.setTotalPrice(BigDecimal.valueOf(dto.getTotalPrice()));
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        return orderRepository.save(order);

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