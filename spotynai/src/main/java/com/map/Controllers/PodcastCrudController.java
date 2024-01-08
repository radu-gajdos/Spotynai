package com.map.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class PodcastCrudController {
        @GetMapping("/podcast-crud")
        public String showPodcastCrud(Model model) {
            return "/podcast-crud";
        }
}
