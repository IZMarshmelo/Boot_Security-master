package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import javax.validation.Valid;



@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/admin/users")
    private String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/admin/users/{id}")
    public String getUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/admin/newUser")
    public String saveUser(User user) {
        return "adduser";
    }

    @PostMapping("/admin/newUser")
    public String add(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "adduser";
        }
        userService.saveUser(user);
        return "redirect:/";
    }


    @DeleteMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/";
    }

    @GetMapping("/admin/updateUser/{id}")
    public String updateUser(@PathVariable("id") Integer id, Model model) {
        model.addAttribute(userService.getUserById(id));
        return "update";
    }

    @PatchMapping("/admin/updateUser")
    public String update(@Valid User updateUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update";
        }
        userService.updateUser(updateUser);
        return "redirect:/";
    }

}

