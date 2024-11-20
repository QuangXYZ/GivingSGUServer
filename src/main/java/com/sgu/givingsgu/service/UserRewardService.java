package com.sgu.givingsgu.service;


import com.sgu.givingsgu.model.Reward;
import com.sgu.givingsgu.model.User;
import com.sgu.givingsgu.model.UserReward;
import com.sgu.givingsgu.repository.RewardRepository;
import com.sgu.givingsgu.repository.UserRepository;
import com.sgu.givingsgu.repository.UserRewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserRewardService {

    @Autowired
    private UserRewardRepository userRewardRepository;

    public List<UserReward> getUserRewards(Long userId) {
        return userRewardRepository.findByUserId(userId);
    }
}
