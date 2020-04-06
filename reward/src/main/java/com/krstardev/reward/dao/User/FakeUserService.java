package com.krstardev.reward.dao.User;

import com.krstardev.reward.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("fakeUsers")
public class FakeUserService implements UserDao {

    private static long user_id = 0;
    private static List<User> DB = new ArrayList<>();

    @Override
    public User insertUser(User user) {
        User userToAdd = new User(++user_id, user.getName());
        DB.add(userToAdd);
        return userToAdd;
    }

    @Override
    public User updateUser(Long id, User update) {
        return selectUserById(id)
                .map(user -> {
                    int indexOfUserToUpdate = DB.indexOf(user);
                    User userToUpdate = new User(id, update.getName());
                    DB.set(indexOfUserToUpdate, userToUpdate);
                    return userToUpdate;
                })
                .orElse(null);
    }

    @Override
    public int deleteUserById(Long id) {
        Optional<User> user = selectUserById(id);
        if(!user.isPresent()) {
            return 0;
        }

        DB.remove(user.get());
        return 1;
    }

    @Override
    public Optional<User> selectUserById(Long id) {
        return DB.stream()
                .filter(user -> (user.getId().equals(id)))
                .findFirst();
    }

    @Override
    public List<User> selectAllUsers() {
        return DB;
    }

}
