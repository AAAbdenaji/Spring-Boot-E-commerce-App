package com.misc.sandboxproj.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.misc.sandboxproj.Helpers.RoleName;
import com.misc.sandboxproj.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName Name);
}
