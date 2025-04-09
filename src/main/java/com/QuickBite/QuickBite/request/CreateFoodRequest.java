package com.QuickBite.QuickBite.request;

import com.QuickBite.QuickBite.model.FoodCategory;
import com.QuickBite.QuickBite.model.IngredientItem;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFoodRequest {

    private String name;
    private String description;
    private Long price;

//    private FoodCategory foodCategory;
    private List<String> images;

    private Long restaurantId;

    private boolean available;

    private boolean vegetarian;
    private boolean seasonal;

//    private List<IngredientItem> ingredients;

}