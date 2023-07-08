package com.hms.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hms.app.entity.Contact;
import com.hms.app.entity.User;
import com.hms.app.repo.UserRepository;
import com.hms.app.service.ContactService;
import com.hms.app.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final UserService userService;
    private final ContactService contactService;

    public HomeController(UserService userService, ContactService contactService) {
        this.userService = userService;
        this.contactService = contactService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user"); // Retrieve the user from the session
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model,HttpServletRequest request) {
        model.addAttribute("userLogin", new User());
        model.addAttribute("currentURI", request.getRequestURI());
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Remove all session attributes
        return "redirect:/";
    }

    @PostMapping("/login")
    public String submitLoginForm(@ModelAttribute User user, HttpSession session,
            RedirectAttributes redirectAttributes) {
        User successfulLogin = userService.login(user.getUsername(), user.getPassword());
        if (successfulLogin != null) {
            // Login successful, redirect to home page
            session.setAttribute("user", successfulLogin);
            return "redirect:/?loggedin";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login?error";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model,HttpSession session) {
        User user = (User) session.getAttribute("user"); // Retrieve the user from the session
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String submitRegistrationForm(@ModelAttribute User user) {
        // Check if user already exists
        User saveUser = userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail());

        if (saveUser == null) {
            // User already exists, return to registration page with error
            return "redirect:/register?error";
        }

        // Registration successful, redirect to login page
        return "redirect:/login?registered";
    }

    @GetMapping("/contact")
    public String contact(Model model,HttpSession session) {
        User user = (User) session.getAttribute("user"); // Retrieve the user from the session
        model.addAttribute("user", user);
        return "contact";
    }

    @PostMapping("/contact")
    public String submitForm(@RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("message") String message) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setEmail(email);
        contact.setMessage(message);
        contactService.saveContact(contact);

        return "redirect:/contact?success";
    }

}