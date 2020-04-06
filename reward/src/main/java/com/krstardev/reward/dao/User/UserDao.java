package com.krstardev.reward.dao.User;

import com.krstardev.reward.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    User insertUser(User user);

    User updateUser(Long id, User user);

    int deleteUserById(Long id);

    Optional<User> selectUserById(Long id);

    List<User> selectAllUsers();

}
