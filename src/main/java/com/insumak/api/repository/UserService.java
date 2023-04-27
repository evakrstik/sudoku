package com.insumak.api.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.insumak.api.model.User;
import com.insumak.api.model.UserDTO;

@Service
public interface UserService {
    
    void saveUser(UserDTO userDto);
    User findUserByEmail(String email);
    List<UserDTO> findAllUsers();
    
}
