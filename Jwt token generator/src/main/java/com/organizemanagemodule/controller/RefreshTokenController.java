package com.organizemanagemodule.controller;

import com.organizemanagemodule.entities.RefreshTokenGenerator;
import com.organizemanagemodule.entities.User;
import com.organizemanagemodule.paylods.LoginResponse;
import com.organizemanagemodule.paylods.RefreshTokenRequest;
import com.organizemanagemodule.security.JwtUtil;
import com.organizemanagemodule.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;
    private final JwtUtil jwtUtil;
    @PostMapping("/refresh")
    public LoginResponse generateRefreshToken(@RequestBody RefreshTokenRequest request){
        RefreshTokenGenerator refreshToken = this.refreshTokenService.verifyRefreshToken(request.getRefreshToken());
        User user = refreshToken.getUser();
        String jwtToken = this.jwtUtil.generateToken(user);
        return LoginResponse.builder()
                .email(user.getEmail())
                .uuId(user.getUuid())
                .refreshToken(request.getRefreshToken())
                .token(jwtToken)
                .build();
    }
}
