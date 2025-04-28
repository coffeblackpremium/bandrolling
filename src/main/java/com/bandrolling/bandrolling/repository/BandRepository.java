package com.bandrolling.bandrolling.repository;

import com.bandrolling.bandrolling.entity.band.Band;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BandRepository extends JpaRepository<Band, Integer> {

    @EntityGraph(attributePaths = {"members"})
    Optional<Band> findById(Integer id);
}
