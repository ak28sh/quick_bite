package com.QuickBite.QuickBite.controller;

import com.QuickBite.QuickBite.Exception.UserException;
import com.QuickBite.QuickBite.service.UserService;
import com.QuickBite.QuickBite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.QuickBite.QuickBite.repository.UserRepository;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserByJwtToken(jwt);
        System.out.println("USER");
        System.out.println(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/get-user-by-email")
    public ResponseEntity<User> findUserByEmail(@RequestBody String email) throws UserException {
        User user = userService.findUserByEmail(email);
        System.out.println("USER");
        System.out.println(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email) {
        // Optional: check if user exists before deleting
        User user = userRepository.findByEmail(email);

        if (user != null) {
            userRepository.deleteByEmail(email);
            return ResponseEntity.ok("✅ User with email " + email + " deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("❌ User with email " + email + " not found.");
        }
    }
}
