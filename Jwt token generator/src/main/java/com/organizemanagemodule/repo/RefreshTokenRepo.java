package com.organizemanagemodule.repo;

import com.organizemanagemodule.entities.RefreshTokenGenerator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshTokenGenerator,Integer> {

    Optional<RefreshTokenGenerator> findByToken(String token);
}
