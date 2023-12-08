package com.map.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomepageController {

    @GetMapping("/homepage")
    public String homepage(@RequestParam("username") String userName, Model model) {

        model.addAttribute("username", userName);

        return "homepage";
    }

}
