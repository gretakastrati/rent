package com.pitagoras.springboot.demo.rent.repository;

import com.pitagoras.springboot.demo.rent.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Integer> {
    @Query("select c from Car c where c.licensePlate = :licensePlate")
    Optional<Car> findByLicensePlate(@Param("licensePlate") String licensePlate);

    Page<Car> findByAvailable(Boolean isAvailable , Pageable pageable);

    Page<Car> findByMakeContainingIgnoreCase(String make, Pageable pageable);

    Page<Car> findByAvailableAndMakeContainingIgnoreCase(Boolean available, String make, Pageable pageable);

}