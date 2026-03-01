package com.example.backend.repository;

import com.example.backend.entity.EmbeddedId.FriendshipId;
import com.example.backend.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends JpaRepository <Friendship, FriendshipId> {
    @Query(
            "select count(f) from Friendship as f " +
            "where f.friend.id = :userId or f.member.id = :userId"
    )
    int countFriendByUserId(@Param("userId") Long userId);
}
