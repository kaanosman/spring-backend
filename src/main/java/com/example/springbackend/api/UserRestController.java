package com.example.springbackend.api;

import com.example.springbackend.model.User;
import com.example.springbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
            Optional<User> user = userService.findById(id);
        if (!user.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(user.get());
    }

    @PostMapping("/users")
    public ResponseEntity create(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User user) {
        if (!userService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!userService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        userService.deleteById(id);

        return ResponseEntity.ok().build();
    }
    @PostMapping("/user/login")
    public String userLogin(@RequestParam String name, @RequestParam String password) {
        User user = userService.findUserById(Long.parseLong(name));
        return user.getId().toString();
    }
}
