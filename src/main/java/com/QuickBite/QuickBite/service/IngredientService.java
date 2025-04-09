package com.QuickBite.QuickBite.service;

import java.util.List;

import com.QuickBite.QuickBite.Exception.FoodException;
import com.QuickBite.QuickBite.Exception.RestaurantException;
import com.QuickBite.QuickBite.model.IngredientCategory;
import com.QuickBite.QuickBite.model.IngredientItem;
import com.QuickBite.QuickBite.model.Food;
import com.QuickBite.QuickBite.repository.IngredientCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public interface IngredientService {

    public IngredientCategory createIngredientsCategory(
            String name,Long restaurantId) throws RestaurantException;

    public IngredientCategory findIngredientsCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception;

    public List<IngredientItem> findRestaurantsIngredients(
            Long restaurantId);


    public IngredientItem createIngredientsItem(Long restaurantId,
                                                 String ingredientName,Long ingredientCategoryId) throws Exception;

    public IngredientItem updateStoke(Long id) throws Exception;

}
