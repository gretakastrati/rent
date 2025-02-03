package com.pitagoras.springboot.demo.rent.rest;

import com.pitagoras.springboot.demo.rent.repository.CarDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pitagoras.springboot.demo.rent.entity.Car;

import java.util.List;

@RestController
@RequestMapping("/cars-dao")
public class CarDAOController {

    private CarDAO carDAO;

    @Autowired
    public CarDAOController(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @PostMapping()
    public Car save(@RequestBody Car carRequest) {

        carRequest.setId(0);
        this.carDAO.save(carRequest);
        return this.carDAO.findById(carRequest.getId());
    }

    @GetMapping("/find/{carId}")
    public Car findById(@PathVariable int carId) {

        Car vetura = this.carDAO.findById(carId);
        if (vetura == null) {
            throw new CarNotFoundException("Car with id " + carId + " not found.");
        }
        return vetura;

    }

    @PutMapping("/{id}")
    public Car updateCar(@RequestBody Car car,@PathVariable int id) {
        Car toUpdateCar = this.carDAO.findById(id);
        if(toUpdateCar == null) {
            throw new CarNotFoundException("Car with id " + id + "not found to update");
        }
        car.setId(id);
        this.carDAO.updateCar(car);
        Car updatedCar = this.carDAO.findById(car.getId());
        return updatedCar;

    }

    @DeleteMapping("/{id}")
    public boolean deleteCar(@PathVariable int id) {
        Car car = this.carDAO.findById(id);
        if (car == null) {
            throw new CarNotFoundException("Car with id "+ id + " was not found");
        }
        carDAO.deleteCar(car);
        return true;
    }

    @GetMapping("/list")
    public List<Car> findAll() {
        List<Car> cars = this.carDAO.findAll();
        return cars;
    }

    @GetMapping("/test-html")
    public String testingHtml() {
        return "<h1>Pitagoras sasad</h1>" +
                "<button>Kliko ketu</button>";

    }

    @GetMapping("/find-car-plate/{licensePlate}")
    public Car findByPlate(@PathVariable String licensePlate) {

        Car vetura = this.carDAO.find(licensePlate);
        if (vetura == null) {
            throw new CarNotFoundException("Car with licensePlate " + licensePlate + " not found.");
        }
        return vetura;

    }
}