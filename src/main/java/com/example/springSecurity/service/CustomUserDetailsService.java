package com.example.springSecurity.service;

import com.example.springSecurity.models.User;
import com.example.springSecurity.models.Userdetails;
import com.example.springSecurity.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findUserByUsername(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        } else
            return new Userdetails(user.get());
    }
}
