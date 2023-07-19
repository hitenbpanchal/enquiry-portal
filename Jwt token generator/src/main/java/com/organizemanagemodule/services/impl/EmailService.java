package com.organizemanagemodule.services.impl;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@SuppressWarnings("SpellCheckingInspection")
@Service
public class EmailService {

//    private LoadingCache<String,Integer> loadingCache;
    private final HashMap<String,Integer> loadingCache = new HashMap<>();

    public Boolean sendEmail(String To){
        boolean flag = false;
        //code
        Properties properties = new Properties();
        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.starttls.enable",true);
        properties.put("mail.smtp.port",587);
        properties.put("mail.smtp.host","smtp.gmail.com");

        String username = "hiten2504";
        String password = "afbxthfbqnjbsbzl";

        //session

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try{
            Message message = new MimeMessage(session);

            String from = "hiten2504@gmail.com";
            String subject = "Reset Password for Enquiry Portal";

            Random random = new Random();
            Integer otp = 100000 + random.nextInt(900000);

            loadingCache.put("OTP",otp);

            String text = "Dear Customer your OTP for reset password is: " + otp;



            message.setRecipient(Message.RecipientType.TO,new InternetAddress(To));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            flag = true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public Boolean verifyOTP(Integer otp) throws ExecutionException {
        return Objects.equals(otp, loadingCache.get("OTP"));
    }
}
