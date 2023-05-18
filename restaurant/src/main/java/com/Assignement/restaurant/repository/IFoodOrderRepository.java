package com.Assignement.restaurant.repository;

import com.Assignement.restaurant.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodOrderRepository extends JpaRepository<FoodOrder , Long> {
}
