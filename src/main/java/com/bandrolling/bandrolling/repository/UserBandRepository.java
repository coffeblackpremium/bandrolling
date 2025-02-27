package com.bandrolling.bandrolling.repository;

import com.bandrolling.bandrolling.entity.UserBand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserBandRepository extends JpaRepository<UserBand, Integer> {

    Optional<UserBand> findByUserIdAndBandId(Integer userId, Integer bandId);
    List<UserBand> findByUserId(Integer userId);
    List<UserBand> findByBandId(Integer bandId);
}
