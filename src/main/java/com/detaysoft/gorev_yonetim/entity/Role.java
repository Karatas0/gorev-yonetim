package com.detaysoft.gorev_yonetim.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.detaysoft.gorev_yonetim.enums.RoleName;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName name;
}