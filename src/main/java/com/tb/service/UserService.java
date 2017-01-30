package com.tb.service;

import com.tb.domain.User;
import com.tb.domain.UserRole;
import com.tb.repository.UserRepository;
import com.tb.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tivadar Bocz on 2017.01.30..
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    public void createUserRole(UserRole userRole) {
        userRoleRepository.save(userRole);
    }
}
