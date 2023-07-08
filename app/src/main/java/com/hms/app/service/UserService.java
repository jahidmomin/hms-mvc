package com.hms.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.app.entity.User;
import com.hms.app.repo.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String username, String password, String email) {
        if (userRepository.findByUsername(username) != null) {
            // User already exists
            return null;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);  // password is encoded before being stored
        user.setEmail(email);

        return userRepository.save(user);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username,password);
        return user;
    }
    
}
