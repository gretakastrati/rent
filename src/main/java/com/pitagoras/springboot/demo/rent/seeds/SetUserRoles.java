package com.pitagoras.springboot.demo.rent.seeds;

import com.pitagoras.springboot.demo.rent.entity.Authority;
import com.pitagoras.springboot.demo.rent.entity.Seed;
import com.pitagoras.springboot.demo.rent.repository.AuthorityRepository;
import com.pitagoras.springboot.demo.rent.repository.SeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Configuration
@EnableTransactionManagement
public class SetUserRoles implements ApplicationRunner {
    private final AuthorityRepository authorityRepository;
    private final SeedRepository seedRepository;

    @Autowired
    public SetUserRoles(AuthorityRepository authorityRepository, SeedRepository seedRepository) {
        this.authorityRepository = authorityRepository;
        this.seedRepository = seedRepository;
    }




    @Override
    public void run(ApplicationArguments args) throws Exception {

        String seedName = "SetUserRoles";
        Optional<Seed> seed = this.seedRepository.findByName(seedName);
        if (seed.isPresent()) {
            return;
        }
        Seed seedToBeExecuted = new Seed();
        seedToBeExecuted.setName(seedName);
        seedToBeExecuted.setSuccess(false);

        Authority manager = new Authority();
        manager.setUserId(1);
        manager.setAuthority("ROLE_MANAGER");
        this.authorityRepository.save(manager);

        Authority employee = new Authority();
        employee.setUserId(2);
        employee.setAuthority("ROLE_EMPLOYEE");
        this.authorityRepository.save(employee);

        Authority admin = new Authority();
        admin.setUserId(3);
        admin.setAuthority("ROLE_ADMIN");
        this.authorityRepository.save(admin);

        seedToBeExecuted.setSuccess(true);
        this.seedRepository.save(seedToBeExecuted);
    }

}
