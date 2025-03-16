package com.pitagoras.springboot.demo.rent.service;

import com.pitagoras.springboot.demo.rent.entity.Review;

import java.util.List;

public interface ReviewService {

    Review postARating(Review review);

    Review findById(Long id);

    Review updateReview (Review theReview);

    List<Review> findAll();

    boolean deleteById(Long id);

    List<Review> findByOrderId (Integer orderId);
}
