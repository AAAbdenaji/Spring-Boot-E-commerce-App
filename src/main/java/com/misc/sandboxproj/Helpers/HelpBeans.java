package com.misc.sandboxproj.Helpers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.misc.sandboxproj.Repositories.RoleRepository;
import com.misc.sandboxproj.models.Role;

@Component
public class HelpBeans {

    @Bean
    CommandLineRunner seedRoles(RoleRepository roleRepository) {
    return args -> {
        System.out.println("SEEDING ROLES RUNNING");
        if (roleRepository.findByName(RoleName.ROLE_USER).isEmpty()) {
            roleRepository.save(new Role(RoleName.ROLE_USER));
        }

        if (roleRepository.findByName(RoleName.ROLE_ADMIN).isEmpty()) {
            roleRepository.save(new Role(RoleName.ROLE_ADMIN));
        }
    };
}
}
