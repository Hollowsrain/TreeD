package com.TreeD.application.util;

import com.TreeD.application.model.entity.User;
import com.TreeD.application.repository.UserRepository;
import com.TreeD.application.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ApplicationUtils {

    private final UserRepository userRepository;

    public Byte[] convertMultiPartToBytes(MultipartFile multipartFile) {

        try {
            Byte[] multipartBytes = new Byte[multipartFile.getBytes().length];

            int i = 0;
            for (byte b : multipartFile.getBytes()) {
                multipartBytes[i++] = b;
            }
            return multipartBytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userRepository.findByUsername(userDetails.getUsername()).get();
        }
        return null;
    }

    public String getUserCurrency() {
        return "EUR";
    }
}