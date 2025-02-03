package com.pitagoras.springboot.demo.rent.service;

import com.pitagoras.springboot.demo.rent.entity.Car;
import com.pitagoras.springboot.demo.rent.repository.CarRepository;
import com.pitagoras.springboot.demo.rent.rest.CarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {


    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car save(Car car) {
        return this.carRepository.save(car);
    }

    @Override
    public Car findById(int id) {

        Optional<Car> vetura = this.carRepository.findById(id);

        if (!vetura.isPresent()) {
            throw new CarNotFoundException("Car with id " + id + " not found.");
        }
        return vetura.get();
    }

    @Override
    public Car updateCar(Car theCar) {
        Optional<Car> toUpdatedCar = this.carRepository.findById(theCar.getId());
        if (toUpdatedCar == null) {
            throw new CarNotFoundException("Car with id " + theCar + " not found to update");
        }

        return this.carRepository.save(theCar);

    }

    @Override
    public List<Car> findAll(Boolean isAvailable) {
        if (isAvailable != null) {
            return carRepository.findByAvailable(isAvailable); // Filtering by availability
        }
        return carRepository.findAll(); // No filter, get all cars
    }

    @Override
    public Car findByLicensePlate(String licensePlate) {
        Optional<Car> vetura = this.carRepository.findByLicensePlate(licensePlate);
        if (!vetura.isPresent()) {
            throw new CarNotFoundException("Car with licensePlate " + licensePlate + " not found.");
        }
        return vetura.get();
    }

    @Override
    public boolean deleteById(int id) {
        Optional<Car> car = this.carRepository.findById(id);
        if (!car.isPresent()) {
            throw new CarNotFoundException("Car with id " + id + " was not found");
        }
        this.carRepository.deleteById(id);
        return true;
    }
}