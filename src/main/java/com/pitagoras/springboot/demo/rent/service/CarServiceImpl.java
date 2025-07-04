package com.pitagoras.springboot.demo.rent.service;

import com.pitagoras.springboot.demo.rent.dto.CarDto;
import com.pitagoras.springboot.demo.rent.entity.Car;
import com.pitagoras.springboot.demo.rent.entity.Order;
import com.pitagoras.springboot.demo.rent.helper.Mapper;
import com.pitagoras.springboot.demo.rent.repository.CarRepository;
import com.pitagoras.springboot.demo.rent.repository.OrderRepository;
import com.pitagoras.springboot.demo.rent.rest.CarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {


    private CarRepository carRepository;
    private OrderRepository orderRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, OrderRepository orderRepository) {
        this.carRepository = carRepository;
        this.orderRepository = orderRepository;
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
    public Page<Car> findAll(Boolean isAvailable, String make, Pageable pageable) {
        if (isAvailable != null && make != null) {
            return carRepository.findByAvailableAndMakeContainingIgnoreCase(isAvailable, make, pageable);
        } else if (isAvailable != null) {
            return carRepository.findByAvailable(isAvailable, pageable);
        } else if (make != null) {
            return carRepository.findByMakeContainingIgnoreCase(make, pageable);
        } else {
            return carRepository.findAll(pageable);
        }
    }

    @Override
    public Page<Car> findAvailableCars(LocalDateTime pickupDateTime, LocalDateTime returnDateTime,
                                       Long carId, String pickupLocation, String dropLocation, Pageable pageable) {

        List<Car> cars;

        // 1. Start from all available cars
        if (carId != null) {
            Optional<Car> carOpt = carRepository.findById(carId.intValue());
            cars = carOpt.map(List::of).orElse(List.of());
        } else {
            cars = carRepository.findByAvailable(true, Pageable.unpaged()).getContent();
        }

        System.out.println(cars.size());

        // 2. Remove cars that are booked in the given date range
        if (pickupDateTime != null && returnDateTime != null) {
            LocalDate pickupDate = pickupDateTime.toLocalDate();
            LocalDate returnDate = returnDateTime.toLocalDate();

            List<Integer> unavailableCarIds = orderRepository.findUnavailableCarIds(pickupDate, returnDate);

            cars = cars.stream()
                    .filter(car -> !unavailableCarIds.contains(car.getId()))
                    .collect(Collectors.toList());
        }

        System.out.println(cars.size());

        // 3. Optionally filter by pickupLocation and dropLocation (if passed)
        if (pickupLocation != null) {
//            System.out.println(cars.get(0).getOrders());
//            cars = cars.stream()
//                    .filter(car -> car.getOrders().stream()
//                            .noneMatch(order -> order.getPickupLocation().equalsIgnoreCase(pickupLocation)))
//                    .collect(Collectors.toList());
        }

        System.out.println(cars.size());

//        if (dropLocation != null) {
//            cars = cars.stream()
//                    .filter(car -> car.getOrders().stream()
//                            .noneMatch(order -> order.getDropLocation().equalsIgnoreCase(dropLocation.toString())))
//                    .collect(Collectors.toList());
//        }


        System.out.println("after drop location not null");

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), cars.size());
        List<Car> paginated = cars.subList(start, end);

        return new PageImpl<>(paginated, pageable, cars.size());

//        return cars;
    }


    private boolean carIsAvailable(Car car, LocalDateTime start, LocalDateTime end) {
        return car.getOrders().stream().noneMatch(order ->
                order.getRentalStartDate().isBefore(ChronoLocalDate.from(end)) &&
                        order.getRentalEndDate().isAfter(ChronoLocalDate.from(start))
        );
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

    @Override
    public boolean isCarAvailable(Long carId, LocalDate pickupDate, LocalDate returnDate, String pickupLocation, String dropLocation) {
        // Kërko rezervimet për atë veturë brenda periudhës së kërkuar
        System.out.println("para se me thirr order Repositury");
        List<Order> overlappingOrders = orderRepository.findOverlappingOrders(carId.intValue(), pickupDate, returnDate);

        if (!overlappingOrders.isEmpty()) {
            return false;
        }

        System.out.println("mas order repositoru");

        // (Opsional) mund të kontrollosh nëse vetura ekziston dhe i përket lokacionit të duhur
        Car car = carRepository.findById(carId.intValue()).orElse(null);
        System.out.println("mas carid");
        if (car == null) return false;

        //if (pickupLocation != null && !pickupLocation.equalsIgnoreCase(car.getPickupLocation())) return false;
        //if (dropLocation != null && !dropLocation.equalsIgnoreCase(car.getDropLocation())) return false;

        return true;
    }
}