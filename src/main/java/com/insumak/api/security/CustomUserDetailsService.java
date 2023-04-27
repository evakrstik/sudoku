package com.insumak.api.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.insumak.api.model.SudokuSession;
import com.insumak.api.model.User;
import com.insumak.api.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    SudokuSession difficulty;

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        
        Collection<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
        // only the default role - USER
        listAuthorities.add(new SimpleGrantedAuthority("USER"));
        
        if (user.getEmail() != null) {
            difficulty.setUsername(user.getUsername());
            difficulty.setGamesWon(user.getGamesWon());
            return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                passwordEncoder.encode(user.getPassword()), 
                listAuthorities
                    );
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}