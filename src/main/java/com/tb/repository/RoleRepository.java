package com.tb.repository;

import com.tb.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Created by Admin on 2017.01.30..
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findByUserId(Long userId);

}
