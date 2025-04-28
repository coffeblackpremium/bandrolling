package com.bandrolling.bandrolling.controller;

import com.bandrolling.bandrolling.dto.BandResponseDto;
import com.bandrolling.bandrolling.dto.CreateBandDto;
import com.bandrolling.bandrolling.entity.band.Band;
import com.bandrolling.bandrolling.service.BandService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "/{bandId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BandResponseDto> getBandById(@PathVariable("bandId") String bandId) {
        var band = bandService.getBandById(bandId);
        return ResponseEntity.ok(band);
    }

    @GetMapping("/all")
    public Page<BandResponseDto> listBands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var pageable = PageRequest.of(page, size);
        return bandService.getAllBands(pageable);
    }
}
