package com.detaysoft.gorev_yonetim.config;

import com.detaysoft.gorev_yonetim.entity.Role;
import com.detaysoft.gorev_yonetim.enums.RoleName;
import com.detaysoft.gorev_yonetim.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        createRoleIfNotExists(RoleName.ADMIN);
        createRoleIfNotExists(RoleName.MANAGER);
        createRoleIfNotExists(RoleName.USER);
    }

    private void createRoleIfNotExists(RoleName roleName) {
        if (roleRepository.findByName(roleName).isEmpty()) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
            log.info("Rol oluşturuldu: {}", roleName);
        }
    }
}