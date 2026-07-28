package com.detaysoft.gorev_yonetim.repository;

import com.detaysoft.gorev_yonetim.entity.Role;
import com.detaysoft.gorev_yonetim.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}