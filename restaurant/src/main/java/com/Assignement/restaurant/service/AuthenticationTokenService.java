package com.Assignement.restaurant.service;


import com.Assignement.restaurant.model.AuthenticationToken;
import com.Assignement.restaurant.model.User;
import com.Assignement.restaurant.repository.IAuthenticationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenService {
    @Autowired
    IAuthenticationTokenRepository iAuthenticationTokenRepository;

    public void saveToken(AuthenticationToken token) {
        iAuthenticationTokenRepository.save(token);
    }

    public AuthenticationToken getToken(User user) {
        return  iAuthenticationTokenRepository.findByUser(user);
    }

    public boolean authenticate(String userEmail, String token) {
        if(token == null && userEmail == null)return false;
        AuthenticationToken authToken = iAuthenticationTokenRepository.findFirstByToken(token);
        if(authToken == null)return false;
        String validEmail = authToken.getUser().getUserEmail();
        return validEmail.equals(userEmail);
    }
}
