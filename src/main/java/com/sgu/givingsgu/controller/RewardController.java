package com.sgu.givingsgu.controller;



import com.sgu.givingsgu.dto.ResponseWrapper;
import com.sgu.givingsgu.model.Reward;
import com.sgu.givingsgu.model.User;
import com.sgu.givingsgu.model.UserReward;
import com.sgu.givingsgu.service.RewardService;
import com.sgu.givingsgu.service.UserRewardService;
import com.sgu.givingsgu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reward")
public class RewardController {


    @Autowired
    private RewardService rewardService;

    @Autowired
    private UserRewardService userRewardService;


    @PostMapping("/redeem")
    public ResponseEntity<ResponseWrapper<UserReward>> redeemReward(
            @RequestParam Long userId,
            @RequestParam Long rewardId) {

        try {
            Optional<UserReward> userReward = rewardService.redeemReward(userId, rewardId);
            if (userReward.isPresent()) {
                return ResponseEntity.ok(new ResponseWrapper<>(200, "Reward redeemed successfully", userReward.get()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseWrapper<>(400, "Insufficient points or invalid reward", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>(500, "An error occurred", null));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<Reward>>> getAllRewards() {
        List<Reward> rewards = rewardService.getAllRewards();
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Successful", rewards));
    }


    @GetMapping("/history/{userId}")
    public ResponseEntity<ResponseWrapper<List<UserReward>>> getRewardHistory(@PathVariable Long userId) {
        List<UserReward> rewards = userRewardService.getUserRewards(userId);
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Successful", rewards));
    }

}

