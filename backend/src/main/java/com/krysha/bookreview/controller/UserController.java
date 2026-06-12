package com.krysha.bookreview.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krysha.bookreview.dto.UserResponse;
import com.krysha.bookreview.model.User;
import com.krysha.bookreview.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Iterable<UserResponse> getUsers() {
        return userService.getUsers().stream()
        		.map(UserResponse::from)
        		.toList();
    }

    @GetMapping("/users/{requestedId}")
    private ResponseEntity<UserResponse> findById(@PathVariable Long requestedId) {
        Optional<User> user = userService.getUser(requestedId);
        if (user.isPresent()) {
            return ResponseEntity.ok(UserResponse.from(user.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/users/{id}")
    private ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{id}")
    private ResponseEntity<UserResponse> putUser(@PathVariable Long id, @RequestBody User userUpdate) {
        if (!userService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        User updated= userService.updateUser(id, userUpdate);
        return ResponseEntity.ok(UserResponse.from(updated));
    }
}