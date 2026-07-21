package com.detaysoft.gorev_yonetim.repository;

import com.detaysoft.gorev_yonetim.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
}