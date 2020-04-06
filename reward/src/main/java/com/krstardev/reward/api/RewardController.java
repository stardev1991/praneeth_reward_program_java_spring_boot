package com.krstardev.reward.api;

import com.krstardev.reward.model.Reward;
import com.krstardev.reward.service.TransactionService;
import com.krstardev.reward.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/v1/reward")
@RestController
public class RewardController {

    private final TransactionService transactionService;
    private final UserService userService;

    @Autowired
    public RewardController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @GetMapping(path = "{id}")
    public double getPointsById(@PathVariable("id") Long id) {
        return transactionService.getPointsById(id);
    }

    @GetMapping(path = "user/{id}")
    public Reward getRewardByUserId(@PathVariable("id") Long id) {
        return transactionService.getPointsByUserId(id);
    }

    @GetMapping
    public List<Reward> getAllRewardsByUser() {
        List<Reward> rewards = new ArrayList<>();
        userService.getAllUsers().forEach(user -> rewards.add(transactionService.getPointsByUserId(user.getId())));
        return rewards;
    }
}
