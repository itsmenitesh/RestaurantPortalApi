package com.Assignement.restaurant.model;

import com.Assignement.restaurant.dto.SignUpInput;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    private String username;
    private String userEmail;
    private String userPassword;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public User(String username, String userEmail, String userPassword ,RoleType roleType) {
        this.username = username;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.roleType = roleType;
    }


}
