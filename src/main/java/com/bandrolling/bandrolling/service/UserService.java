package com.bandrolling.bandrolling.service;

import com.bandrolling.bandrolling.dto.CreateUserDto;
import com.bandrolling.bandrolling.entity.User;
import com.bandrolling.bandrolling.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository)  {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserDto createUserDto) {
        User user = new User(createUserDto.name(), createUserDto.email(), createUserDto.password(), Instant.now(), Instant.now());
        return userRepository.save(user);
    }

    public User getUserById(String userId) {
        var user = userRepository.findById(Integer.parseInt(userId));
        if (user.isPresent()) {
            return user.get();
        }
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return null;
    }

    public Page<User> listUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
