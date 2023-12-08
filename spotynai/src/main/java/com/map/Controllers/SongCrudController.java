package com.map.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SongCrudController {
    @GetMapping("/song-crud")
    public String showSongCrud(Model model) {
        return "/song-crud";
    }

}
