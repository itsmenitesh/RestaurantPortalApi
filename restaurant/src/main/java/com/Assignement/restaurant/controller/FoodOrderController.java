package com.Assignement.restaurant.controller;

import com.Assignement.restaurant.model.FoodOrder;
import com.Assignement.restaurant.service.AuthenticationTokenService;
import com.Assignement.restaurant.service.FoodItemService;
import com.Assignement.restaurant.service.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foodOrder")
public class FoodOrderController {
    @Autowired
    FoodOrderService foodOrderService;


    @Autowired
    AuthenticationTokenService authenticationTokenService;

//placeFoodOrder
    @PostMapping()
    public String placeFoodOrder(@RequestParam String userEmail , @RequestParam String token , @RequestBody FoodOrder foodOrder){
        if(authenticationTokenService.authenticate(userEmail,token)){
            foodOrderService.placeFoodOrder(foodOrder ,token);
            return "food has been order";

        }else {
            return "admin not allowed to order food";
        }
    }

    //getaAllFood

    @GetMapping()
    public List<FoodOrder> getAllPlacedFoodOrder(){
        return foodOrderService.getAllOrderFood();
    }
}

