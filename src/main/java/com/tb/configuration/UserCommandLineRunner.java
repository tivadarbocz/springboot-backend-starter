package com.tb.configuration;

import com.tb.domain.Role;
import com.tb.domain.User;
import com.tb.repository.RoleRepository;
import com.tb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by Tivadar Bocz on 2017.01.30..
 */
@Component
public class UserCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        // save a couple of customers
        Role adminRole = new Role(1L, "ADMIN");
        Role userRole = new Role(1L, "USER");
        roleRepository.saveAndFlush(adminRole);
        roleRepository.saveAndFlush(userRole);
        User user = new User("user", new BCryptPasswordEncoder().encode("user"), false);


        repository.save(user);
        roleRepository.save(userRole);
        log.info("-------------------------------");
        log.info("Finding all users");
        log.info("-------------------------------");
        for (User u : repository.findAll()) {
            log.info(u.toString());
        }
    }

}
