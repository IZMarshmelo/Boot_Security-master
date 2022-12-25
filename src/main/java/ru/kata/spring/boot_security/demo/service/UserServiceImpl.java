package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.security.SecurityUser;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {


    private final UserDao userDao;
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserDao userDao, UserRepository userRepository) {
        this.userDao = userDao;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void updateUser(@Valid User updateUser) {
        userDao.updateUser(updateUser);
    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User findUserById(Long userId) {
        return userDao.findUserById(userId);
    }

    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Override
    public boolean saveUserRep(User user) {
        return userDao.saveUserRep(user);
    }

    @Override
    public boolean deleteUser(Long userId) {
        return userDao.deleteUser(userId);
    }

    @Override
    public List<User> userList(Long idMin) {
        return userDao.userList(idMin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return new SecurityUser(user.get());
    }
}
