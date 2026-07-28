package com.detaysoft.gorev_yonetim.repository;

import com.detaysoft.gorev_yonetim.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}