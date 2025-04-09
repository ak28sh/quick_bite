package com.QuickBite.QuickBite.service;

import com.QuickBite.QuickBite.Exception.FoodException;
import com.QuickBite.QuickBite.model.Food;
import com.QuickBite.QuickBite.model.FoodCategory;
import com.QuickBite.QuickBite.model.Restaurant;
import com.QuickBite.QuickBite.repository.FoodRepository;
import com.QuickBite.QuickBite.repository.RestaurantRepository;
import com.QuickBite.QuickBite.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImplementation implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;


    @Override
    public Food createFood(CreateFoodRequest req, Restaurant restaurant) throws FoodException {

        Food food = new Food();
//        food.setFoodCategory(foodCategory);
        food.setCreationDate(new Date());
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice((long) req.getPrice());
//        food.setSeasonal(req.getisSe));
        food.setVegetarian(req.isVegetarian());
//        food.setAvailable(req.getAvailable);
//        food.setIngredients(req.getIngredients());
        food = foodRepository.save(food);

        restaurant.getFoods().add(food);
        return food;
    }

    @Override
    public void deleteFood(Long foodId) throws FoodException {
        Food food = findFoodById(foodId);
        foodRepository.delete(food);
        food.setRestaurant(null);
    }

//    @Override
//    public List<Food> getRestaurantFood(Long restaurantId, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal, String foodCategory) throws FoodException {
//        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
//        if (isVegetarian) {
//            foods = filterByVegetarian(foods, isVegetarian);
//        }
//        if (isNonVeg) {
//            foods = filterByNonVeg(foods, isNonVeg);
//        }
//        if (isSeasonal) {
//            foods = filterBySeasonal(foods, isSeasonal);
//        }
//        if (foodCategory != null && !foodCategory.equals("")) {
//            foods = filterByFoodCategory(foods, foodCategory);
//        }
//        return foods;
//    }

    @Override
    public List<Food> getRestaurantFood(Long restaurantId, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal) throws FoodException {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
        if (isVegetarian) {
            foods = filterByVegetarian(foods, isVegetarian);
        }
        if (isNonVeg) {
            foods = filterByNonVeg(foods, isNonVeg);
        }
        if (isSeasonal) {
            foods = filterBySeasonal(foods, isSeasonal);
        }
        return foods;
    }

    @Override
    public List<Food> getRestaurantAllFood(Long restaurantId) throws FoodException {
        List<Food> allfoods = foodRepository.findByRestaurantId(restaurantId);
        System.out.print(allfoods);
        return allfoods;
    }

//    private List<Food> filterByFoodCategory(List<Food> foods, String foodCategory) {
//        return foods.stream().
//                filter(food -> {
//                    if (food.getFoodCategory() != null) {
//                        return food.getFoodCategory().getName().equals(foodCategory);
//                    }
//                    return false;
//                }).
//                collect(Collectors.toList());
//    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
        return foods.stream().
                filter(food -> food.isVegetarian() == isVegetarian).
                collect(Collectors.toList());
    }

    private List<Food> filterByNonVeg(List<Food> foods, boolean isNonVeg) {
        return foods.stream().
                filter(food -> food.isNonVeg() == isNonVeg).
                collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream()
                .filter(food -> food.isSeasonal() == isSeasonal)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<Food> searchFood(String keyword) {
//        List<Food> foods = new ArrayList<>();
//        if(!keyword.equals("")) {
//            foods = foodRepository.searchFoodByNameOrCategory(keyword);
//        }
//
//        return foods;
//    }

    @Override
    public Food findFoodById(Long foodId) throws FoodException {
        Optional<Food> food = foodRepository.findById(foodId);
        if (food.isPresent()) {
            return food.get();
        }
        throw new FoodException("Food not found with" + foodId + "Id");
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws FoodException {

        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        foodRepository.save(food);
        return food;
    }
}
