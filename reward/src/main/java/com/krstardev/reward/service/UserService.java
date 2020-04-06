package com.krstardev.reward.service;

import com.krstardev.reward.dao.User.UserDao;
import com.krstardev.reward.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("fakeUsers") UserDao userDao) {
        this.userDao = userDao;
    }

    public User addUser(User user) {
        return userDao.insertUser(user);
    }

    public User updateUser(Long id, User update) {
        return userDao.updateUser(id, update);
    }

    public int deleteUserById(Long id) {
        return userDao.deleteUserById(id);
    }

    public Optional<User> getUserById(Long id) {
        return userDao.selectUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
    }

}
