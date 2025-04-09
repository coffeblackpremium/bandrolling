package com.bandrolling.bandrolling.service;

import com.bandrolling.bandrolling.dto.AddUserToBand;
import com.bandrolling.bandrolling.dto.CreateBandDto;
import com.bandrolling.bandrolling.entity.UserBand;
import com.bandrolling.bandrolling.entity.band.Band;
import com.bandrolling.bandrolling.repository.BandRepository;
import com.bandrolling.bandrolling.repository.UserBandRepository;
import com.bandrolling.bandrolling.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        try {
            var band = new Band.Builder()
                    .name(createBandDto.name())
                    .genre(createBandDto.genre())
                    .description(createBandDto.description())
                    .location(createBandDto.location())
                    .image(createBandDto.profileImage())
                    .createdAt(Instant.now())
                    .updatedAt(null)
                    .build();
            return bandRepository.save(band);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error creating band: " + e.getMessage(), e);
        }
    }

    public Band getBandById(String bandId) {
        return bandRepository.findById(Integer.parseInt(bandId))
                .orElseThrow(() -> new RuntimeException("Band not found"));
    }

    public UserBand addMemberToBand(AddUserToBand addUserToBand) {
        var band = bandRepository.findById(addUserToBand.bandId())
                .orElseThrow(() -> new RuntimeException("Band not found"));
        var user = userRepository.findById(addUserToBand.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var userBand = new UserBand.Builder()
                .user(user)
                .band(band)
                .joinedAt(Instant.now())
                .role(addUserToBand.role()).build();
        return userBandRepository.save(userBand);
    }

    public Page<Band> getAllBands(Pageable pageable) {
        return bandRepository.findAll(pageable);
    }
}
