package com.QuickBite.QuickBite.service;

import com.QuickBite.QuickBite.Exception.RestaurantException;
import com.QuickBite.QuickBite.model.FoodCategory;
import com.QuickBite.QuickBite.model.Restaurant;
import com.QuickBite.QuickBite.repository.FoodCategoryRepository;
import com.QuickBite.QuickBite.repository.FoodRepository;
import com.QuickBite.QuickBite.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class FoodCategoryServiceImplementation implements FoodCategoryService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    // Create Food Category
    @Override
    public FoodCategory createFoodCategory(String name, Long userId) throws RestaurantException {

        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        System.out.printf("restaurant", restaurant);
        FoodCategory newfoodCategory = new FoodCategory();
        newfoodCategory.setName(name);
        newfoodCategory.setRestaurant(restaurant);
        System.out.printf("foodCategory", newfoodCategory);
        return foodCategoryRepository.save(newfoodCategory);
    }

    // Find all food category in a restaurant
    @Override
    public List<FoodCategory> getCategoryByRestaurantId(Long restaurantId) throws RestaurantException {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        System.out.printf("restaurant", restaurant);
        return foodCategoryRepository.findFoodCategoryByRestaurantId(restaurantId);
    }

    @Override
    public FoodCategory findCategoryById(Long id) throws RestaurantException {
        Optional<FoodCategory> opt = foodCategoryRepository.findById(id);
        if(opt.isEmpty()) {
            throw new RestaurantException("Food category not found with id " + id);
        }
        return opt.get();
    }
}
