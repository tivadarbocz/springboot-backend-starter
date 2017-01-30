package com.tb.security;

import com.tb.domain.Role;
import com.tb.domain.User;
import com.tb.repository.RoleRepository;
import com.tb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tivadar Bocz on 2017.01.30..
 */
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                LOGGER.debug("user not found with the provided username");
                return null;
            }
            LOGGER.debug(" user from username " + user.toString());

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private Set<GrantedAuthority> getAuthorities(User user) {
        //TODO kiszedni roleokat usernek
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        //for(Role role : user.getRoles()) {
        /*for(Role role : roleRepository.getUserRoles(user.getId())) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
        authorities.add(grantedAuthority);
        }*/
        for (Role role : roleRepository.findByUserId(user.getId())) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }
        LOGGER.debug("user authorities are " + authorities.toString());
        //user.setRoleSet(roleRepository.getUserRoles());
        return authorities;
    }


}