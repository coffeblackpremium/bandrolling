package com.bandrolling.bandrolling.controller;

import com.bandrolling.bandrolling.dto.AddUserToBand;
import com.bandrolling.bandrolling.entity.UserBand;
import com.bandrolling.bandrolling.service.BandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user-band")
public class UserBandController {

    private final BandService bandService;

    public UserBandController(BandService bandService) {
        this.bandService = bandService;
    }

    // Add a new endpoint to add a user to a band
    @PostMapping("/add-user-to-band")
    public ResponseEntity<UserBand> addUserToBand(@RequestBody AddUserToBand addUserToBand) {
        var userBand = bandService.addMemberToBand(addUserToBand);
        return ResponseEntity.ok(userBand);
    }
}
