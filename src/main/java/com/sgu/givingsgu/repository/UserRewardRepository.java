package com.sgu.givingsgu.repository;

import com.sgu.givingsgu.model.Project;
import com.sgu.givingsgu.model.UserReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRewardRepository extends JpaRepository<UserReward, Long> {
}
