package com.organizemanagemodule.services.impl;

import com.organizemanagemodule.exceptions.UserNotFoundException;
import com.organizemanagemodule.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class ResetPasswordService {

    private final EmailService emailService;
    private final UserRepo userRepo;
    public String sentOTP(String email){
        if (!this.userRepo.existsByEmail(email)){
            throw new UserNotFoundException(email,0);
        }
        this.emailService.sendEmail(email);
        return "OTP sent successfully";
    }

    public Boolean verifyOTP(Integer otp) throws ExecutionException {
       return this.emailService.verifyOTP(otp);
    }
}
