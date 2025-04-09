package com.QuickBite.QuickBite.service;

import com.QuickBite.QuickBite.Exception.FoodException;
import com.QuickBite.QuickBite.model.Food;
import com.QuickBite.QuickBite.model.FoodCategory;
import com.QuickBite.QuickBite.model.Restaurant;
import com.QuickBite.QuickBite.request.CreateFoodRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodService {

    public Food createFood(CreateFoodRequest req, Restaurant restaurant) throws FoodException;

    public void deleteFood(Long foodId) throws FoodException;

//    public List<Food> getRestaurantFood(Long restaurantId, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal, String foodCategory) throws FoodException;
    public List<Food> getRestaurantFood(Long restaurantId, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal) throws FoodException;

    public List<Food> getRestaurantAllFood(Long restaurantId) throws FoodException;
//    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws FoodException;

    public Food updateAvailabilityStatus(Long foodId) throws FoodException;
}
