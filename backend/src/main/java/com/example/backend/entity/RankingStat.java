package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "ranking_stat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RankingStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;
    @Column(name = "play_count", nullable = false)
    private long playCount;
    @Column(name = "saved_count", nullable = false)
    private long savedCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id")
    private Track track;
}
