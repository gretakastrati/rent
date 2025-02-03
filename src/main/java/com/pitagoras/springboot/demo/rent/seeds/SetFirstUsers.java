package com.pitagoras.springboot.demo.rent.seeds;

import com.pitagoras.springboot.demo.rent.entity.Car;
import com.pitagoras.springboot.demo.rent.entity.Seed;
import com.pitagoras.springboot.demo.rent.entity.User;
import com.pitagoras.springboot.demo.rent.repository.CarRepository;
import com.pitagoras.springboot.demo.rent.repository.SeedRepository;
import com.pitagoras.springboot.demo.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Configuration
public class SetFirstUsers implements ApplicationRunner {

    private final UserRepository userRepository;
    private final SeedRepository seedRepository;

    @Autowired
    public SetFirstUsers(UserRepository  userRepository , SeedRepository seedRepository) {
        this.userRepository = userRepository;
        this.seedRepository = seedRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String seedName = "SetFirstUsers";
        Optional<Seed> seed = this.seedRepository.findByName(seedName);
        if(seed.isPresent()) {
            return;
        }

        Seed seedToBeExecuted= new Seed();
        seedToBeExecuted.setName(seedName);
        seedToBeExecuted.setSuccess(false);
        User greta = new User();
        greta.setUserId(0);
        greta.setUsername("greta");
        greta.setPassword("{noop}greta123");
        greta.setEnable(true);
        greta.setEmail("greta@gmail.com");
        greta.setName("greta");
        this.userRepository.save(greta);

        User rigoni = new User();
        rigoni.setUserId(0);
        rigoni.setUsername("rigoni");
        rigoni.setPassword("{noop}rigoni123");
        rigoni.setEnable(true);
        rigoni.setEmail("rigon@gmail.com");
        rigoni.setName("rigoni");
        this.userRepository.save(rigoni);

        User toni = new User();
        toni.setUserId(0);
        toni.setUsername("toni");
        toni.setPassword("{noop}toni123");
        toni.setEnable(true);
        toni.setEmail("toni@gmail.com");
        toni.setName("toni");
        this.userRepository.save(toni);



        seedToBeExecuted.setCreatedAt(LocalDateTime.now());
        Seed savedSeed = this.seedRepository.save(seedToBeExecuted);


        savedSeed.setSuccess(true);
        savedSeed.setUpdatedAt(LocalDateTime.now());
        this.seedRepository.save(savedSeed);

    }
}