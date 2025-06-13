package com.pitagoras.springboot.demo.rent.service;

import com.pitagoras.springboot.demo.rent.entity.Car;
import com.pitagoras.springboot.demo.rent.entity.CarImage;
import com.pitagoras.springboot.demo.rent.repository.CarImageRepository;
import com.pitagoras.springboot.demo.rent.repository.CarRepository;
import jakarta.transaction.Transactional;

import java.util.List;

public class CarImageServiceImpl implements CarImageService {

    private final CarRepository carRepository;
    private final CarImageRepository carImageRepository;

    public CarImageServiceImpl(CarRepository carRepository, CarImageRepository carImageRepository) {
        this.carRepository = carRepository;
        this.carImageRepository = carImageRepository;
    }

    @Transactional
    public CarImage addImageToCar(int carId, String imageUrl, boolean primary) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if (primary) {
            carImageRepository.findByCarIdAndPrimaryImageTrue(carId)
                    .ifPresent(existingPrimary -> {
                        existingPrimary.setPrimaryImage(false);
                        carImageRepository.save(existingPrimary);
                    });
        }

        CarImage image = new CarImage();
        image.setCar(car);
        image.setImageUrl(imageUrl);
        image.setPrimaryImage(primary);

        return carImageRepository.save(image);
    }

    public List<CarImage> getImagesByCar(int carId) {

        return carImageRepository.findByCarId(carId);
    }
}
