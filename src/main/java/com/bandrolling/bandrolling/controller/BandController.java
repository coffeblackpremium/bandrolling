package com.bandrolling.bandrolling.controller;

import com.bandrolling.bandrolling.dto.CreateBandDto;
import com.bandrolling.bandrolling.entity.band.Band;
import com.bandrolling.bandrolling.service.BandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/bands")
public class BandController {

    private final BandService bandService;

    private BandController(BandService bandService) {
        this.bandService = bandService;
    }

    // Return type should be ResponseEntity<Band>
    @PostMapping("/create")
    public ResponseEntity<Band> createBand(@RequestBody CreateBandDto createBandDto)  {
        var band = bandService.createBand(createBandDto);
        return ResponseEntity.created(URI.create("/v1/bands/create/" + band.getId())).body(band);
    }

    @GetMapping("/{bandId}")
    public ResponseEntity<Band> getBandById(@PathVariable("bandId") String bandId) {
        var band = bandService.getBandById(bandId);
        return ResponseEntity.ok(band);
    }
}
