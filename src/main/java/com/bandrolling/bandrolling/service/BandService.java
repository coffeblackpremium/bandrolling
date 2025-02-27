package com.bandrolling.bandrolling.service;

import com.bandrolling.bandrolling.dto.CreateBandDto;
import com.bandrolling.bandrolling.entity.UserBand;
import com.bandrolling.bandrolling.entity.band.Band;
import com.bandrolling.bandrolling.repository.BandRepository;
import com.bandrolling.bandrolling.repository.UserBandRepository;
import com.bandrolling.bandrolling.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class BandService {
    private final BandRepository bandRepository;
    private final UserRepository userRepository;
    private final UserBandRepository userBandRepository;

    public BandService(BandRepository bandRepository, UserRepository userRepository, UserBandRepository userBandRepository) {
        this.bandRepository = bandRepository;
        this.userRepository = userRepository;
        this.userBandRepository = userBandRepository;
    }

    public Band createBand(CreateBandDto createBandDto) {
        var band = new Band.Builder().name(createBandDto.name())
                .genre(createBandDto.genre())
                .description(createBandDto.description())
                .location(createBandDto.location())
                .image(createBandDto.profileImage())
                .createdAt(Instant.now())
                .updatedAt(null)
                .build();
        return bandRepository.save(band);
    }

    public Band getBandById(String bandId) {
        return bandRepository.findById(Integer.parseInt(bandId))
                .orElseThrow(() -> new RuntimeException("Band not found"));
    }

    public UserBand addMemberToBand(String bandId, String userId) {
        var band = bandRepository.findById(Integer.parseInt(bandId))
                .orElseThrow(() -> new RuntimeException("Band not found"));
        var user = userRepository.findById(Integer.parseInt(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));
        var userBand = new UserBand.Builder()
                .user(user)
                .band(band)
                .joinedAt(Instant.now())
                .role("Membro").build();
        return userBandRepository.save(userBand);
    }
}
