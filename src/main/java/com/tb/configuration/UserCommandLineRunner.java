package com.tb.configuration;

import com.tb.domain.User;
import com.tb.domain.UserRole;
import com.tb.repository.UserRepository;
import com.tb.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void run(String... args) {
        // save a couple of customers
        Set<UserRole> userRoles = new HashSet<>();
        User user = new User("user", new BCryptPasswordEncoder().encode("user"), true);
        UserRole userRole = new UserRole(user, "admin");


        repository.save(user);
        userRoleRepository.save(userRole);
        log.info("-------------------------------");
        log.info("Finding all users");
        log.info("-------------------------------");
        for (User u : repository.findAll()) {
            log.info(u.toString());
        }
    }

}
