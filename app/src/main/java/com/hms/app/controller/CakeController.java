package com.hms.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hms.app.service.CakeService;

@Controller
public class CakeController {
    @Autowired
    private CakeService cakeService;

    @GetMapping("/cakes")
    public String getCakes(Model model) {
        model.addAttribute("cakes", cakeService.getAllCakes());
        return "cakes";
    }
}
