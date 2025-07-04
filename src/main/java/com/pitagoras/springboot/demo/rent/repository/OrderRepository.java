package com.pitagoras.springboot.demo.rent.repository;

import com.pitagoras.springboot.demo.rent.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Integer customerId);

    @Query("SELECT o FROM Order o WHERE o.carId = :carId AND o.rentalEndDate >= :startDate AND o.rentalStartDate <= :endDate")
    List<Order> findOverlappingOrders(@Param("carId") int carId,
                                      @Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate);

    @Query("SELECT o.car.id FROM Order o WHERE " +
            "(:pickupDate <= o.rentalEndDate AND :returnDate >= o.rentalStartDate)")
    List<Integer> findUnavailableCarIds(@Param("pickupDate") LocalDate pickupDate,
                                        @Param("returnDate") LocalDate returnDate);


}

