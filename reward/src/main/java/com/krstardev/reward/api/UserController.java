package com.krstardev.reward.api;

import com.krstardev.reward.model.User;
import com.krstardev.reward.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User addUser(@Valid @NotBlank @RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        if(userService.deleteUserById(id) > 0) {
            return "Success";
        }
        return "Failure";
    }

    @PutMapping(path = "{id}")
    public User updateUser(@PathVariable("id") Long id,
                                  @Valid @NotBlank @RequestBody User user) {
        return userService.updateUser(id, user);
    }

}
