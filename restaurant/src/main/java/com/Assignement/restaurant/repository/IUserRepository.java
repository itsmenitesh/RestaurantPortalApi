package com.Assignement.restaurant.repository;

import com.Assignement.restaurant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface IUserRepository extends JpaRepository<User , Long> {
    User findFirstByUserEmail(String userEmail);
}
