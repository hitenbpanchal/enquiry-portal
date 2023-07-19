package com.organizemanagemodule.services;

import com.organizemanagemodule.entities.RefreshTokenGenerator;
import com.organizemanagemodule.paylods.LoginResponse;

public interface RefreshTokenService {

    RefreshTokenGenerator createToken(String username);
    RefreshTokenGenerator verifyRefreshToken(String refreshToken);
}
