package com.Assignement.restaurant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Integer quantity;

    @OneToMany
    private List<FoodItem> foodItemId;

    @ManyToOne
    private User userId;

    @Enumerated(EnumType.STRING)
    private FoodStatus foodStatus;

}
