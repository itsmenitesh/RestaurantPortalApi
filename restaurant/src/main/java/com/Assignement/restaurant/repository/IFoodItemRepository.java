package com.Assignement.restaurant.repository;

import com.Assignement.restaurant.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodItemRepository extends JpaRepository<FoodItem ,Long> {
}
