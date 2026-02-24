package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(name = "cover_url", length = 150, nullable = false)
    private String coverUrl;
    @Column(name = "realease_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate realeaseDate;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "artist_id")
    private ArtistProfile artist;

    @OneToMany(
            mappedBy = "album",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AlbumTrack> tracks;

    @OneToMany(
            mappedBy = "album",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PlayerState> playerStates;
}
