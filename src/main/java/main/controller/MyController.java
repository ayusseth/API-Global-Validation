package main.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import main.entity.User;
import main.exception.UserNotFoundException;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createuser(@Valid @RequestBody User user) {
        log.info("Received request to create new User..");
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUser() {
        log.info("Received request to fetch all User..");
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        log.info("Received request to fetch user with ID={}",id);
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        log.info("Received request to update user with ID={}",id);
        User newUser = userService.updateUser(id, user);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        log.info("Received request to delete user with ID={}",id);
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
    }

}
