package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import javax.validation.Valid;
import java.util.List;

public interface UserService extends UserDetailsService {

    void saveUser(User user);

    void updateUser(@Valid User updateUser);

    void removeUserById(long id);

    List<User> getAllUsers();

    User getUserById(long id);

    User findUserById(Long userId);

    List<User> allUsers();

    boolean saveUserRep(User user);

    boolean deleteUser(Long userId);

    List<User> userList(Long idMin);

}