package com.bandrolling.bandrolling.repository;

import com.bandrolling.bandrolling.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
