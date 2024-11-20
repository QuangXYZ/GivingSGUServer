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
public class RewardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private UserRewardRepository userRewardRepository;


    public Optional<UserReward> redeemReward(Long userId, Long rewardId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Reward> rewardOpt = rewardRepository.findById(rewardId);

        if (userOpt.isPresent() && rewardOpt.isPresent()) {
            User user = userOpt.get();
            Reward reward = rewardOpt.get();

            if (user.getPoints() >= reward.getPointsRequired()) {
                user.setPoints(user.getPoints() - reward.getPointsRequired());
                userRepository.save(user);

                UserReward userReward = new UserReward();
                userReward.setUserId(user.getUserId());
                userReward.setReward(reward);
                userReward.setRedeemDate(LocalDateTime.now());
                userReward.setStatus("pending");
                userRewardRepository.save(userReward);

                return Optional.of(userReward);
            }
        }
        return Optional.empty();
    }

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }
}
