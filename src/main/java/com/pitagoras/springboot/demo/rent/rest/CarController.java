package com.pitagoras.springboot.demo.rent.rest;

import com.pitagoras.springboot.demo.rent.dto.CarDto;
import com.pitagoras.springboot.demo.rent.helper.Mapper;
import com.pitagoras.springboot.demo.rent.repository.CarRepository;
import com.pitagoras.springboot.demo.rent.entity.Car;
import com.pitagoras.springboot.demo.rent.service.CarService;
import com.pitagoras.springboot.demo.rent.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final OrderService orderService;


    @Autowired
    public CarController( CarService carService, OrderService orderService) {

        this.carService = carService;
        this.orderService = orderService;
    }

    @GetMapping("/list")
    public Page<CarDto> findAll(@RequestParam(required = false) Boolean isAvailable,
                                @RequestParam(required = false) String make,
                                @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        Page<Car> carsPage = carService.findAll(isAvailable, make, pageable);
        return carsPage.map(Mapper::convertToDto);
    }

    @GetMapping("/search-available")
    public ResponseEntity<Page<CarDto>> searchAvailableCars(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime pickupDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDate,
            @RequestParam(required = false) Long carId,
            @RequestParam(required = false) String pickupLocation,
            @RequestParam(required = false) String dropLocation,
            @PageableDefault(size = 100, sort = "id") Pageable pageable
    ) {
        Page<Car> availableCarsPage = carService.findAvailableCars(
                pickupDate, returnDate, carId, pickupLocation, dropLocation, pageable
        );

        Page<CarDto> dtoPage = availableCarsPage.map(Mapper::convertToDto);

        return ResponseEntity.ok(dtoPage);
    }


    @PostMapping()
    public Car save(@RequestBody Car carRequest) {

        return this.carService.save(carRequest);
    }

    @GetMapping("/find/{carId}")
    public ResponseEntity<CarDto    > findById(@PathVariable int carId) {
       Car vetura = this.carService.findById(carId);
       if(vetura != null) {
           CarDto carDto = Mapper.convertToDto(vetura);
           return ResponseEntity.ok(carDto);
       }

        return null;
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
    @GetMapping("/check-availability")
    public ResponseEntity<Boolean> checkAvailability(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime pickupDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDate,
            @RequestParam Long carId,
            @RequestParam(required = false) String pickupLocation,
            @RequestParam(required = false) String dropLocation
            ) {

        boolean isAvailable = carService.isCarAvailable(
                carId,
                pickupDate.toLocalDate(),
                returnDate.toLocalDate(),
                pickupLocation,
                dropLocation
        );
        return ResponseEntity.ok(isAvailable);
    }

}









