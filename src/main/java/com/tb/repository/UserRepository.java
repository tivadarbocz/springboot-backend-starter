package com.tb.repository;

import com.tb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Tivadar Bocz on 2017.01.30..
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    void deleteById(Long id);
}
