package com.map.Controllers;

import com.map.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        boolean isAuthenticated = userService.authenticateUser(username, password);

        if (isAuthenticated) {
            // Redirect to a success page or perform other actions
            return "redirect:/songs";
        } else {
            // Add an error message to the model and return to the login page
            return "login";
        }
    }
}
