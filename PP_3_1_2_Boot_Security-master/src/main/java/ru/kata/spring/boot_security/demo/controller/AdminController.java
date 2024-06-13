package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUsers(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("role", roleService.findAll());
        model.addAttribute("user", user);
        model.addAttribute("new_user", new User());
        return "admin/admin";
    }

    @PostMapping("/create")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam (value = "rolesForController", required = false) List<String> roles ) {
        userService.addUser(user, roles);
        return "redirect:/admin";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam (value = "rolesForController", required = false) List<String> roles) {
        userService.updateUser(user, roles);
        return "redirect:/admin";

    }

    @PostMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id,
                             @ModelAttribute("user") User user) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
