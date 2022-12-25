package ru.kata.spring.boot_security.demo.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Component
public class UserDaoHibernate implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
        entityManager.close();
    }

    @Override
    public void updateUser(@Valid User updateUser) {
        entityManager.merge(updateUser);
    }

    @Override
    public void removeUserById(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }
    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public boolean saveUserRep(User user) {
        user.setRoles("ROLE_USER");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
    @Override
    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
    @Override
    public List<User> userList(Long idMin) {
        return entityManager.createQuery("select u from User u where u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
}