package com.organizemanagemodule.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizemanagemodule.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {
	
	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);

}
