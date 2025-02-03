package com.pitagoras.springboot.demo.rent.rest;

import com.pitagoras.springboot.demo.rent.repository.CarRepository;
import com.pitagoras.springboot.demo.rent.entity.Car;
import com.pitagoras.springboot.demo.rent.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;


    @Autowired
    public CarController( CarService carService) {

        this.carService = carService;
    }

    @GetMapping("/list")
    public List<Car> findAll(@RequestParam(required = false) Boolean isAvailable) {
        List<Car> cars = this.carService.findAll(isAvailable);
        return cars;

    }

    @PostMapping()
    public Car save(@RequestBody Car carRequest) {

        return this.carService.save(carRequest);
    }

    @GetMapping("/find/{carId}")
    public Car findById(@PathVariable int carId) {

       Car vetura = this.carService.findById(carId);

        return vetura;
    }
    @PutMapping("/{id}")
    public Car updateCar(@RequestBody Car car, @PathVariable int id) {
        Car toUpdateCar = this.carService.findById(id);
        if(toUpdateCar == null) {
            throw new CarNotFoundException("Car with id " + id + "not found to update.");
        }
        car.setId(id);
        this.carService.save(car);
        return toUpdateCar;
    }
    @DeleteMapping("/{id}")
    public boolean deleteCar(@PathVariable int id) {
        return carService.deleteById(id);
    }
    @GetMapping("/find-car-plate/{licensePlate}")
    public Car findByPlate(@PathVariable String licensePlate) {
        return carService.findByLicensePlate(licensePlate);


    }
}









