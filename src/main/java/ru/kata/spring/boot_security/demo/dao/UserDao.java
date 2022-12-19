package ru.kata.spring.boot_security.demo.dao;



import ru.kata.spring.boot_security.demo.model.User;

import javax.validation.Valid;
import java.util.List;

public interface UserDao {

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