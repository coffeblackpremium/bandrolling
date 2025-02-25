package com.bandrolling.bandrolling.service;

import com.bandrolling.bandrolling.dto.CreateUserDto;
import com.bandrolling.bandrolling.dto.UpdateUserDto;
import com.bandrolling.bandrolling.entity.User;
import com.bandrolling.bandrolling.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder)  {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserDto createUserDto) {
        String hashedPassword = passwordEncoder.encode(createUserDto.password());
        User user = new User(createUserDto.name(), createUserDto.email(), hashedPassword, Instant.now(), Instant.now());
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

    public User updateUserById(String userId, UpdateUserDto updateUserDto)  {
        var user = userRepository.findById(Integer.parseInt(userId));
        if (user.isPresent()) {
            var userEntity = user.get();
            if (updateUserDto.name() != null) {
                userEntity.setName(updateUserDto.name());
            }
            if (updateUserDto.email() != null) {
                userEntity.setEmail(updateUserDto.email());
            }
            if (updateUserDto.password() != null) {
                String hashedPassword = passwordEncoder.encode(updateUserDto.password());
                userEntity.setPassword(hashedPassword);
            }
            return userRepository.save(userEntity);
        }
        throw new RuntimeException("User not found");
    }
}
