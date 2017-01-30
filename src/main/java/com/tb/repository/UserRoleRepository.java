package com.tb.repository;

import com.tb.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Tivadar Bocz on 2017.01.30..
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
