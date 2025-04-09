package com.QuickBite.QuickBite.controller;

import com.QuickBite.QuickBite.Exception.FoodException;
import com.QuickBite.QuickBite.model.Food;
import com.QuickBite.QuickBite.service.FoodService;
import com.QuickBite.QuickBite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class MenuItemController {

    @Autowired
    private FoodService menuItemService;

    @Autowired
    private UserService userService;

//    @GetMapping("/search")
//    public ResponseEntity<List<Food>> searchFood(
//            @RequestParam String name
//    ) {
//        List<Food> menuItem = menuItemService.searchFood(name);
//        return ResponseEntity.ok(menuItem);
//    }

    @GetMapping("restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getMenuItemByRestaurantId(
            @PathVariable Long restaurantId
//            @RequestParam boolean vegetarian,
//            @RequestParam boolean seasonal,
//            @RequestParam boolean nonveg
    ) throws FoodException
    {
        List<Food> menuItems= menuItemService.getRestaurantAllFood(restaurantId);
        return ResponseEntity.ok(menuItems);
    }
}
