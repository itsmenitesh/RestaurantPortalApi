package com.Assignement.restaurant.repository;

import com.Assignement.restaurant.model.AuthenticationToken;
import com.Assignement.restaurant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationTokenRepository extends JpaRepository<AuthenticationToken , Long> {
    AuthenticationToken findByUser(User user);

    AuthenticationToken findFirstByToken(String token);
}
