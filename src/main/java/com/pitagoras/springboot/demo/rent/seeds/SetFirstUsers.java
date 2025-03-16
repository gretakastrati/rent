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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Configuration
@EnableTransactionManagement
public class SetFirstUsers implements ApplicationRunner {

    private final UserRepository userRepository;
    private final SeedRepository seedRepository;

    @Autowired
    public SetFirstUsers(UserRepository userRepository, SeedRepository seedRepository) {
        this.userRepository = userRepository;
        this.seedRepository = seedRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String seedName = "SetFirstUsers";
        Optional<Seed> seed = this.seedRepository.findByName(seedName);
        if(seed.isPresent()){
            return;
        }
        Seed seedToBeExecuted = new Seed();
        seedToBeExecuted.setName(seedName);
        seedToBeExecuted.setSuccess(false);

        Seed savedSeed = this.seedRepository.save(seedToBeExecuted);

        User agoni = new User();
        agoni.setUserId(0);
        agoni.setName("Agon");
        agoni.setEmail("agoni@gmail.com");
        agoni.setUsername("agon");
        agoni.setPassword("{noop}agoni123");
        agoni.setEnabled(true);

        this.userRepository.save(agoni);

        User rigoni = new User();
        rigoni.setUserId(0);
        rigoni.setName("Rigoni");
        rigoni.setEmail("rig@gmail.com");
        rigoni.setUsername("rigon");
        rigoni.setPassword("{noop}rigon123");
        rigoni.setEnabled(true);

        this.userRepository.save(rigoni);

        User toni = new User();
        toni.setUserId(0);
        toni.setName("Arton");
        toni.setEmail("toni@gmail.com");
        toni.setUsername("toni");
        toni.setPassword("{noop}toni123");
        toni.setEnabled(true);

        this.userRepository.save(toni);

        savedSeed.setSuccess(true);
        this.seedRepository.save(savedSeed);

    }
}