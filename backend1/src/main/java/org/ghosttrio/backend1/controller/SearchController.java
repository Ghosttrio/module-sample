package org.ghosttrio.backend1.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class SearchController {

    @GetMapping("/search")
    public String search() {
        return "ok";
    }
}
