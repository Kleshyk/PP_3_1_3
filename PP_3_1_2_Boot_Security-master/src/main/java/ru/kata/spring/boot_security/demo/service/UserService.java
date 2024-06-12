package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    void addUser(User user, List<String> roles);

    void deleteUser(Long id);

    void updateUser(User user, List<String> roles);

    Optional<User> getUserById(Long id);

    User findByUsername(String username);

    User findOne(Long id);
}
