package com.pitagoras.springboot.demo.rent.service;

import com.pitagoras.springboot.demo.rent.entity.Customer;
import com.pitagoras.springboot.demo.rent.entity.Order;
import com.pitagoras.springboot.demo.rent.entity.Review;
import com.pitagoras.springboot.demo.rent.repository.OrderRepository;
import com.pitagoras.springboot.demo.rent.repository.ReviewRepository;
import com.pitagoras.springboot.demo.rent.rest.CarNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, OrderRepository orderRepository) {
        this.reviewRepository = reviewRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Review postARating(Review review) {
        Review r = new Review();
        r.setComment(review.getComment());
        r.setRating(review.getRating());
        r.setOrderId(review.getOrderId());

        Optional<Order> o = this.orderRepository.findById(review.getOrderId().longValue());

        if (!o.isPresent()) {
            throw new CarNotFoundException("Order with id " + review.getOrderId() + " not found.");
        }
        r.setOrder(o.get());
       return this.reviewRepository.save(r);
    }




    @Override
    public Review findById(Long id) {
        Optional<Review> review = this.reviewRepository.findById(id);

        if(!review.isPresent()) {
            throw new CarNotFoundException("Review with id " + " not found.");
        }
        return review.get();
    }

    @Override
    public Review updateReview(Review theReview) {
        return null;
    }

    @Override
    public List<Review> findAll() {
        return this.reviewRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Review> review = this.reviewRepository.findById(id);
        if (!review.isPresent()) {
            throw new CarNotFoundException("Review with id " + id + " was not found");
        }
        this.reviewRepository.deleteById(id);
        return true;

    }

    @Override
    public List<Review> findByOrderId(Integer orderId) {
        return this.reviewRepository.findByOrderId(orderId);
    }
}
