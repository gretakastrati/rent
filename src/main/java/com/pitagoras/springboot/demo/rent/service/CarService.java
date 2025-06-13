package com.pitagoras.springboot.demo.rent.service;

import com.pitagoras.springboot.demo.rent.dto.CarDto;
import com.pitagoras.springboot.demo.rent.entity.Car;
import com.pitagoras.springboot.demo.rent.helper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public interface CarService {

    Car save(Car vetura);
    Car findById (int id);
    Car updateCar (Car theCar);
    Page<Car> findAll(Boolean isAvailable, String make, Pageable pageable);
    Car findByLicensePlate(String licensePlate);
    boolean deleteById(int id);

    Page<Car> findAvailableCars(LocalDateTime pickupDate, LocalDateTime returnDate,
                                          Long carId, String pickupLocation, String dropLocation, Pageable pageable);

}
