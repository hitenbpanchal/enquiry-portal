package com.organizemanagemodule.services.impl;

import com.organizemanagemodule.entities.RefreshTokenGenerator;
import com.organizemanagemodule.entities.User;
import com.organizemanagemodule.exceptions.UserNotFoundException;
import com.organizemanagemodule.repo.RefreshTokenRepo;
import com.organizemanagemodule.repo.UserRepo;
import com.organizemanagemodule.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class RefreshTokenImpl implements RefreshTokenService {

    private final UserRepo userRepo;
    private final RefreshTokenRepo tokenRepo;
    private final int timeout = 2*60*1000;
    @Override
    public RefreshTokenGenerator createToken(String username) {
        User user = userRepo.findByEmail(username).orElseThrow(() -> new UserNotFoundException("email", 0));
        RefreshTokenGenerator refreshToken1 = user.getRefreshToken();
        if(refreshToken1 == null){
            refreshToken1 = RefreshTokenGenerator.builder()
                    .token(UUID.randomUUID().toString())
                    .expiry(Instant.now().plusMillis(timeout))
                    .user(user)
                    .build();
        }else {
            refreshToken1 = RefreshTokenGenerator.builder()
                    .token(refreshToken1.getToken())
                    .expiry(Instant.now().plusMillis(timeout))
                    .build();
        }
        return tokenRepo.save(refreshToken1);

    }
    @Override
    public RefreshTokenGenerator verifyRefreshToken(String refreshToken){
        RefreshTokenGenerator token = tokenRepo.findByToken(refreshToken).orElseThrow(() -> new IllegalStateException("Refresh token"));

        if (token.getExpiry().compareTo(Instant.now())<0){
            tokenRepo.delete(token);
            throw new IllegalStateException("Refresh token Expired!!");
        }
        return token;
    }
}
