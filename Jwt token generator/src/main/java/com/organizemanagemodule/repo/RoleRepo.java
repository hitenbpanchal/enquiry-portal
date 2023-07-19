package com.organizemanagemodule.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizemanagemodule.entities.ERole;
import com.organizemanagemodule.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(ERole role);
}
