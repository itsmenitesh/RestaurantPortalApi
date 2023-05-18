package com.Assignement.restaurant.service;

import com.Assignement.restaurant.model.AuthenticationToken;
import com.Assignement.restaurant.model.FoodItem;
import com.Assignement.restaurant.repository.IAuthenticationTokenRepository;
import com.Assignement.restaurant.repository.IFoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {
    @Autowired
    IFoodItemRepository iFoodItemRepository;

    @Autowired
    IAuthenticationTokenRepository iAuthenticationTokenRepository;

    public void addFoodItem(FoodItem foodItem, String token) {
        AuthenticationToken authToken = iAuthenticationTokenRepository.findFirstByToken(token);
        if(authToken !=null){
            iFoodItemRepository.save(foodItem);
        }

    }

    public List<FoodItem> getAllFoodItem() {
        return iFoodItemRepository.findAll();
    }
}
