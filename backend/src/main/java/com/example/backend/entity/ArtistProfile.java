package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "artist_profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "stage_name", length = 50, nullable = false)
    private String stageName;
    @Column(length = 500)
    private String bio;
    @Column(name = "is_verified", nullable = false)
    private boolean isVerified;
    @Column(name = "avatar_url", length = 150, nullable = false)
    private String avatarUrl;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(
            mappedBy = "artist",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Album> albums;

    @OneToMany(
            mappedBy = "contributor",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<ArtistContribution> contributions;

    @OneToMany(
            mappedBy = "artist",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Follower> followers;
}
