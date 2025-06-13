package com.pitagoras.springboot.demo.rent.repository;

import com.pitagoras.springboot.demo.rent.entity.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarImageRepository extends JpaRepository<CarImage, Long> {
    List<CarImage> findByCarId(int carId);

    Optional<CarImage> findByCarIdAndPrimaryImageTrue(int carId);
}


