package com.pitagoras.springboot.demo.rent.service;

import com.pitagoras.springboot.demo.rent.entity.Car;

import java.util.List;

public interface CarService {

    Car save(Car vetura);
    Car findById (int id);
    Car updateCar (Car theCar);
    List<Car> findAll(Boolean isAvailable);
    Car findByLicensePlate(String licensePlate);
    boolean deleteById(int id);




}
