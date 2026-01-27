package com.example.backend.repository;

import com.example.backend.Enum.UserStatus;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
    @Modifying
    @Query("update User u set u.status = :status where u.email = :email")
    void updateUserStatus(@Param("email") String email, @Param("status") UserStatus status);
}

