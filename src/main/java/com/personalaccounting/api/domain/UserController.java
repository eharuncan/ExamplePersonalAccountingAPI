package com.personalaccounting.api.domain;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/api/v1/users")
    List<User> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/api/v1/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    // Single item

    @GetMapping("/api/v1/users/{id}")
    User one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/api/v1/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
                    user.setType(newUser.getType());
                    user.setName(newUser.getName());
                    user.setSurname(newUser.getSurname());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/api/v1/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}