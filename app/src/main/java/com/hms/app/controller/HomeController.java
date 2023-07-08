package com.hms.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hms.app.dto.User;
import com.hms.app.repo.UserRepository;

@Controller
public class HomeController {

    private final UserRepository userRepository;

    @Autowired
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String submitLoginForm(@ModelAttribute User user, Model model) {
        com.hms.app.entity.User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            model.addAttribute("username", user.getUsername());
            return "index";
        } else {
            // User does not exist or entered password is incorrect
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String submitRegistrationForm(@ModelAttribute User user) {
        // Check if user already exists
        com.hms.app.entity.User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            // User already exists, return error
            return "register";
        }

        // User doesn't exist, save new user
        com.hms.app.entity.User newUser = new com.hms.app.entity.User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        userRepository.save(newUser);
        return "login";
    }
}