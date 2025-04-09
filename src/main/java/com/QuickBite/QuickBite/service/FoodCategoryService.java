package com.QuickBite.QuickBite.service;

import java.util.List;

import com.QuickBite.QuickBite.Exception.RestaurantException;
import com.QuickBite.QuickBite.model.FoodCategory;
import org.springframework.stereotype.Service;

@Service
public interface FoodCategoryService {

    public FoodCategory createFoodCategory (String name, Long userId) throws RestaurantException;
    public List<FoodCategory> getCategoryByRestaurantId(Long restaurantId) throws RestaurantException;
    public FoodCategory findCategoryById(Long id) throws RestaurantException;

}
