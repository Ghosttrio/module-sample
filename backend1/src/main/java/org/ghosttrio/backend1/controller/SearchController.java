package org.ghosttrio.backend1.controller;

import lombok.RequiredArgsConstructor;
import org.ghosttrio.backend1.service.UserService;
import org.ghosttrio.backend1.service.response.UserResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SearchController {

    private final UserService userService;

    @GetMapping("/search")
    public UserResponse search() {
        return userService.algorithmA();
    }
}
