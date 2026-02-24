package com.example.backend.entity;

import com.example.backend.Enum.TrackStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(name = "thumbnail_url",length = 150, nullable = false)
    private String thumbnailUrl;
    @Column(nullable = false)
    private int duration;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrackStatus status;

    @OneToMany(
            mappedBy = "track",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TrackTag> tags;

    @OneToMany(
            mappedBy = "track",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PlaylistTrack> playlists;

    @OneToMany(
            mappedBy = "track",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AlbumTrack> albums;

    @OneToMany(
            mappedBy = "track",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ArtistContribution> contributions;

    @OneToMany(
            mappedBy = "track",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserInteraction> interactions;

    @OneToMany(
            mappedBy = "track",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RankingStat> rankingStats;

    @OneToMany(
            mappedBy = "track",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PlayerState> playerStates;
}
