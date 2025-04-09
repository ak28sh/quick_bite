package com.QuickBite.QuickBite.controller;

import com.QuickBite.QuickBite.service.FoodService;
import com.QuickBite.QuickBite.service.RestaurantService;
import com.QuickBite.QuickBite.service.UserService;
import com.QuickBite.QuickBite.model.Food;
import com.QuickBite.QuickBite.model.FoodCategory;
import com.QuickBite.QuickBite.model.Restaurant;
import com.QuickBite.QuickBite.model.User;
import com.QuickBite.QuickBite.repository.FoodRepository;
import com.QuickBite.QuickBite.request.CreateFoodRequest;
import com.QuickBite.QuickBite.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private UserService userService;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodService foodService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/create")
    public ResponseEntity<Food> createFood(
            @RequestHeader("Authorization") String jwt,
            @RequestBody CreateFoodRequest req
    ) throws Exception {
        System.out.print("CreateFoodReq");
        System.out.print(req);

        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
//        FoodCategory foodCategory = req.getFoodCategory();
        Food food = foodService.createFood(req, restaurant);
        foodRepository.save(food);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{foodId}")
    public ResponseEntity<MessageResponse> deleteFood(
            @PathVariable Long foodId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(foodId);

        MessageResponse res = new MessageResponse();
        res.setMessage("Food deleted successfully");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{foodId}")
    public  ResponseEntity<Food> updateAvailabilityStatus(
            @PathVariable Long foodId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailabilityStatus(foodId);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
