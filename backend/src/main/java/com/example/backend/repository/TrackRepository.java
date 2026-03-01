package com.example.backend.repository;

import com.example.backend.Enum.TrackStatus;
import com.example.backend.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackRepository extends JpaRepository <Track, Long>{
    List<Track> findAllByStatus(TrackStatus status);
}
