package com.Assignement.restaurant.service;

import com.Assignement.restaurant.dto.SignInInput;
import com.Assignement.restaurant.dto.SignInOutput;
import com.Assignement.restaurant.dto.SignUpInput;
import com.Assignement.restaurant.dto.SignUpOutput;
import com.Assignement.restaurant.model.AuthenticationToken;
import com.Assignement.restaurant.model.RoleType;
import com.Assignement.restaurant.model.User;
import com.Assignement.restaurant.repository.IUserRepository;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    AuthenticationTokenService authenticationTokenService;


    public SignUpOutput singUp(SignUpInput signUpDto) {
        //1st. check if user already exists or not using user Email
//        Patient patient = iPatientRepository.findFirstByPatientEmail(signUpDto.getUserEmail());
        User user = iUserRepository.findFirstByUserEmail(signUpDto.getUserEmail());
        if(user != null) {
            throw new IllegalStateException("This UserEmail already Exists !!! .. Please try to Login");
        }


        //2nd. password encryption
        String encryptedPassword = null ;
        try {
            encryptedPassword = encryptPassword(signUpDto.getUserPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        //3rd. save the user data

        user = new User(signUpDto.getUsername() , signUpDto.getUserEmail() ,encryptedPassword , signUpDto.getRoleType());
        iUserRepository.save(user);


        //4th. Token create
        AuthenticationToken token = new AuthenticationToken(user);
        authenticationTokenService.saveToken(token);



        //5th. return singUpOutput
        return  new SignUpOutput("User registered" , "Account Created successfully");
    }

    //encryption method
    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5") ;
        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);
        return hash;
    }



    // sign in
    public SignInOutput signIn(SignInInput signInInDto) {
        //1st. check email exists
        User user = iUserRepository.findFirstByUserEmail(signInInDto.getUserEmail());
        if(user == null) {
            throw new IllegalStateException("This User is not Exists !!! .. Please try to Create account or please signUp");
        }

        //2nd. encrypt the password
        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signInInDto.getUserPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


        //3rd. match it with database encrypted password

        boolean isPasswordValid = encryptedPassword.equals(user.getUserPassword());
        if(!isPasswordValid){
            throw new IllegalStateException("Incorrect Password !!! .. Please re-check");
        }

        //4th. figure out the token
        AuthenticationToken authToken = authenticationTokenService.getToken(user);
        //5th. set up output response
        return new SignInOutput("Authentication successfully " , authToken.getToken());
    }
}
