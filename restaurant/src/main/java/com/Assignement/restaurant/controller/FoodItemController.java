package com.Assignement.restaurant.controller;

import com.Assignement.restaurant.model.FoodItem;
import com.Assignement.restaurant.service.AuthenticationTokenService;
import com.Assignement.restaurant.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foodItem")
public class FoodItemController {
    @Autowired
    FoodItemService foodItemService;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    //addFood
    @PostMapping()
    public String addFoodItem(@RequestParam String userEmail , @RequestParam String token , @RequestBody FoodItem foodItem){
        if(authenticationTokenService.authenticate(userEmail , token)){
            foodItemService.addFoodItem(foodItem , token);
            return " New FoodItem has been added...!!!";

        }else {
            return "sorry its not acceptable because only admin can add food";

        }

    }

    //getAllFoodItem
    @GetMapping()
    public List<FoodItem> getAllFood(){
        return foodItemService.getAllFoodItem();
    }

}
