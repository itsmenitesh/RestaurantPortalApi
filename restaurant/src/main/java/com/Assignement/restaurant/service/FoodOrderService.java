package com.Assignement.restaurant.service;


import com.Assignement.restaurant.model.AuthenticationToken;
import com.Assignement.restaurant.model.FoodOrder;
import com.Assignement.restaurant.model.FoodStatus;
import com.Assignement.restaurant.repository.IAuthenticationTokenRepository;
import com.Assignement.restaurant.repository.IFoodOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodOrderService {
    @Autowired
    IFoodOrderRepository iFoodOrderRepository;

    @Autowired
    IAuthenticationTokenRepository iAuthenticationTokenRepository;

    public void placeFoodOrder(FoodOrder foodOrder, String token) {
        AuthenticationToken authToken = iAuthenticationTokenRepository.findFirstByToken(token);
        foodOrder.setFoodStatus(FoodStatus.Created);
        foodOrder.setUserId(authToken.getUser());
        iFoodOrderRepository.save(foodOrder);



    }

    public List<FoodOrder> getAllOrderFood() {
        return iFoodOrderRepository.findAll();
    }
}
