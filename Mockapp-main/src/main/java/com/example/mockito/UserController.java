package com.example.mockito;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/save")
    public ResponseEntity<Users> createUser(@RequestBody Users users){
        return new ResponseEntity<>(userService.createUser(users), HttpStatus.CREATED);
    }
}
