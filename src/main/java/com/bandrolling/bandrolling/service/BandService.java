package com.bandrolling.bandrolling.service;

import com.bandrolling.bandrolling.dto.AddUserToBand;
import com.bandrolling.bandrolling.dto.BandResponseDto;
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
import java.util.List;

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

    public BandResponseDto getBandById(String bandId) {
        Band band = bandRepository.findById(Integer.parseInt(bandId))
                .orElseThrow(() -> new RuntimeException("Band not found"));

        List<UserBand> members = userBandRepository.findAllByBandId(band.getId());

        List<BandResponseDto.MemberDto> memberDtos = members.stream()
                .map(member -> new BandResponseDto.MemberDto(
                        member.getUser().getId(),
                        member.getUser().getName(),
                        member.getRole()
                )).toList();

        BandResponseDto response = new BandResponseDto();
        response.setId(band.getId());
        response.setName(band.getName());
        response.setMembers(memberDtos);

        return response;
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

    public Page<BandResponseDto> getAllBands(Pageable pageable) {
        Page<Band> bands = bandRepository.findAll(pageable);

        return bands.map(band -> {
            List<UserBand> members = userBandRepository.findAllByBandId(band.getId());

            List<BandResponseDto.MemberDto> memberDtos = members.stream()
                    .map(member -> new BandResponseDto.MemberDto(
                            member.getUser().getId(),
                            member.getUser().getName(),
                            member.getRole()
                    )).toList();

            BandResponseDto response = new BandResponseDto();
            response.setId(band.getId());
            response.setName(band.getName());
            response.setMembers(memberDtos);

            return response;
        });
    }
}
