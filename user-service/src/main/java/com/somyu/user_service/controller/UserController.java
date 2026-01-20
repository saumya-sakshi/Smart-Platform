package com.somyu.user_service.controller;

import com.somyu.user_service.entity.User;
import com.somyu.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    private final UserRepository repo;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }
}
