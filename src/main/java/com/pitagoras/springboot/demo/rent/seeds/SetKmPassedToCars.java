package com.pitagoras.springboot.demo.rent.seeds;

import com.pitagoras.springboot.demo.rent.repository.CarRepository;
import com.pitagoras.springboot.demo.rent.repository.SeedRepository;
import com.pitagoras.springboot.demo.rent.entity.Car;
import com.pitagoras.springboot.demo.rent.entity.Seed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Configuration
public class SetKmPassedToCars implements ApplicationRunner {

    private final CarRepository carRepository;
    private final SeedRepository seedRepository;

    @Autowired
    public SetKmPassedToCars(CarRepository carRepository ,SeedRepository seedRepository) {
        this.carRepository = carRepository;
        this.seedRepository = seedRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String seedName = "SetKmPassedToCars";
        Optional<Seed> seed = this.seedRepository.findByName(seedName);
        if(seed.isPresent()) {
            return;
        }

        Seed seedToBeExecuted= new Seed();
        seedToBeExecuted.setName(seedName);
        seedToBeExecuted.setSuccess(false);
        seedToBeExecuted.setCreatedAt(LocalDateTime.now());
        Seed savedSeed = this.seedRepository.save(seedToBeExecuted);

        List<Car> cars =this.carRepository.findAll();

        Random rand = new Random();

        for(Car car : cars) {
            int kmPassed = rand.nextInt(10000,200000);
            car.setKmPassed(kmPassed);
            this.carRepository.save(car);

        }
        savedSeed.setSuccess(true);
        savedSeed.setUpdatedAt(LocalDateTime.now());
        this.seedRepository.save(savedSeed);

    }
}