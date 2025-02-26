package com.bandrolling.bandrolling.repository;

import com.bandrolling.bandrolling.entity.band.Band;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BandRepository extends JpaRepository<Band, Integer> {
}
