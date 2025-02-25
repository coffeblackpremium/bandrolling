package com.bandrolling.bandrolling.controller;

import com.bandrolling.bandrolling.dto.CreateUserDto;
import com.bandrolling.bandrolling.entity.User;
import com.bandrolling.bandrolling.repository.UserRepository;
import com.bandrolling.bandrolling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        var userId = userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/v1/users/create/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> showUser(@PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/list")
    public Page<User> listUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var pageable = PageRequest.of(page, size);
        return userService.listUsers(pageable);
    }
}
