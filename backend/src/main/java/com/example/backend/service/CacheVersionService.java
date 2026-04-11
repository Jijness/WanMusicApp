package com.example.backend.service;

public interface CacheVersionService {
    long getJamNotificationVersion(Long jamSessionId);
    void bumpJamNotificationVersion(Long jamSessionId);
    long getTrackVersion();
    void bumpTrackVersion();
    long getArtistVersion();
    void bumpArtistVersion();
    long getAlbumVersion();
    void bumpAlbumVersion();
}
