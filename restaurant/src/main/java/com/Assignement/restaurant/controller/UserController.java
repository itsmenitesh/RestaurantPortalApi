package com.Assignement.restaurant.controller;

import com.Assignement.restaurant.dto.SignInInput;
import com.Assignement.restaurant.dto.SignInOutput;
import com.Assignement.restaurant.dto.SignUpInput;
import com.Assignement.restaurant.dto.SignUpOutput;
import com.Assignement.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    //sign up
    @PostMapping("/signup")
    public SignUpOutput signUp(@RequestBody SignUpInput signUpDto){
        return userService.singUp(signUpDto);
    }

    //SingIn
    @PostMapping("/signing")
    public SignInOutput signup(@RequestBody SignInInput signInInDto){
        return  userService.signIn(signInInDto);
    }
}
