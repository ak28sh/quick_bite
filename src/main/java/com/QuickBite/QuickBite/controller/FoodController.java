package com.QuickBite.QuickBite.controller;

import com.QuickBite.QuickBite.service.FoodService;
import com.QuickBite.QuickBite.service.UserService;
import com.QuickBite.QuickBite.model.Food;
import com.QuickBite.QuickBite.model.User;
import com.QuickBite.QuickBite.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/Food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private FoodRepository foodRepository;

//    @PostMapping("/search")
//    public ResponseEntity<List<Food>> searchFood(
//            @RequestParam String keyword,
//            @RequestHeader("Authorization") String jwt
//    ) throws Exception {
//        User user = userService.findUserByJwtToken(jwt);
//        List<Food> foods = foodService.searchFood(keyword);
//
//        return new ResponseEntity<>(foods, HttpStatus.OK);
//    }


//    @GetMapping("/restaurant/{restaurantId}")
//    public ResponseEntity<List<Food>> getRestaurantFood(
//            @PathVariable Long restaurantId,
//            @RequestHeader("Authorization") String jwt,
//            @RequestParam  boolean isVegetarian,
//            @RequestParam boolean isNonVeg,
//            @RequestParam boolean isSeasonal
////            @RequestParam(required = false) String foodCategory
//
//    ) throws Exception {
//        User user = userService.findUserByJwtToken(jwt);
//        List<Food> foods = foodService.getRestaurantFood(restaurantId, isVegetarian, isNonVeg, isSeasonal);
//        return new ResponseEntity<>(foods, HttpStatus.OK);
//    }


}