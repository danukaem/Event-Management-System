package com.assignment.EventManagementSystem.service;

import com.assignment.EventManagementSystem.entity.User;
import com.assignment.EventManagementSystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userOld = null;
        if( userRepository.findByUsername(username).isPresent()){
             userOld = userRepository.findByUsername(username).get();
        }
        if (userOld == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userOld.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(userOld.getUsername(), userOld.getPassword(), authorities);
    }
}