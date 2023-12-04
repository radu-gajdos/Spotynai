package com.map.Controllers;

import org.springframework.ui.Model;
import com.map.Domain.entities.UserEntity;
import com.map.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:63342")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        boolean isAuthenticated = userService.authenticateUser(username, password);

        if (isAuthenticated) {
            model.addAttribute("userDisplayName", username);

            return "homepage";
        } else {
            return "login";
        }
    }

    @PostMapping("/signup")
    @CrossOrigin(origins = "http://localhost:63342")
    public String signup(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        if (userService.isUsernameTaken(username)) {
            return "redirect:/signup?error=username_taken";
        }

        UserEntity newUser = new UserEntity(null, username,email, password, null, null, "User");
        userService.createUser(newUser);

        return "redirect:/login";
    }
}

