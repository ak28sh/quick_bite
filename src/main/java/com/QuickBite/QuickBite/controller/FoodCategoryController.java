package com.QuickBite.QuickBite.controller;

import com.QuickBite.QuickBite.Exception.RestaurantException;
import com.QuickBite.QuickBite.Exception.UserException;
import com.QuickBite.QuickBite.model.FoodCategory;
import com.QuickBite.QuickBite.model.Restaurant;
import com.QuickBite.QuickBite.model.User;
import com.QuickBite.QuickBite.service.FoodCategoryService;
import com.QuickBite.QuickBite.service.FoodService;
import com.QuickBite.QuickBite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FoodCategoryController {

    @Autowired
    private FoodCategoryService foodCategoryService;

    @Autowired
    private UserService userService;


    @PostMapping("/admin/food-category")
    public ResponseEntity<FoodCategory> createFoodCategory(
            @RequestHeader("Authorization") String jwt,
            @RequestBody FoodCategory foodCategory
    ) throws RestaurantException, UserException {
        User user = userService.findUserByJwtToken(jwt);
        FoodCategory newFoodCategory = foodCategoryService.createFoodCategory(foodCategory.getName(), user.getId());
        return new ResponseEntity<>(newFoodCategory, HttpStatus.CREATED);
    }

    @GetMapping("food-category/restaurant/{restaurantId}")
    public ResponseEntity<List<FoodCategory>> getCategoryByRestaurantId(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long restaurantId
    ) throws RestaurantException {
        List<FoodCategory> foodCategories = foodCategoryService.getCategoryByRestaurantId(restaurantId);
        return new ResponseEntity<>(foodCategories, HttpStatus.OK);
    }


}
