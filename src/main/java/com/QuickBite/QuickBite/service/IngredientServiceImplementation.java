package com.QuickBite.QuickBite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuickBite.QuickBite.Exception.FoodException;
import com.QuickBite.QuickBite.Exception.RestaurantException;
import com.QuickBite.QuickBite.model.IngredientCategory;
import com.QuickBite.QuickBite.model.IngredientItem;
import com.QuickBite.QuickBite.model.Food;
import com.QuickBite.QuickBite.model.Restaurant;
import com.QuickBite.QuickBite.repository.IngredientCategoryRepository;
import com.QuickBite.QuickBite.repository.IngredientItemRepository;

@Service
public class IngredientServiceImplementation implements IngredientService {

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepo;

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientsCategory(
            String name,Long restaurantId) throws RestaurantException {

        IngredientCategory isExist=ingredientCategoryRepo
                .findByRestaurantIdAndNameIgnoreCase(restaurantId,name);

        if(isExist!=null) {
            return isExist;
        }

        Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);

        IngredientCategory ingredientCategory=new IngredientCategory();
        ingredientCategory.setRestaurant(restaurant);
        ingredientCategory.setName(name);

        IngredientCategory createdCategory = ingredientCategoryRepo.save(ingredientCategory);

        return createdCategory;
    }

    @Override
    public IngredientCategory findIngredientsCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt=ingredientCategoryRepo.findById(id);
        if(opt.isEmpty()){
            throw new Exception("ingredient category not found");
        }
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception {
        return ingredientCategoryRepo.findByRestaurantId(id);
    }

    @Override
    public List<IngredientItem> findRestaurantsIngredients(Long restaurantId) {

        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }


    @Override
    public IngredientItem createIngredientsItem(Long restaurantId,
                                                 String ingredientName, Long ingredientCategoryId) throws Exception {

        IngredientCategory category = findIngredientsCategoryById(ingredientCategoryId);

        IngredientItem isExist = ingredientItemRepository.
                findByRestaurantIdAndNameIngoreCase(restaurantId, ingredientName,category.getName());
        if(isExist!=null) {
            System.out.println("is exists-------- item");
            return isExist;
        }

        Restaurant restaurant=restaurantService.findRestaurantById(
                restaurantId);
        IngredientItem item=new IngredientItem();
        item.setName(ingredientName);
        item.setRestaurant(restaurant);
        item.setIngredientCategory(category);
        IngredientItem savedIngredients = ingredientItemRepository.save(item);
        category.getIngredientItem().add(savedIngredients);

        return savedIngredients;
    }


    @Override
    public IngredientItem updateStoke(Long id) throws Exception {
        Optional<IngredientItem> item=ingredientItemRepository.findById(id);
        if(item.isEmpty()) {
            throw new Exception("ingredient not found with id "+item);
        }
        IngredientItem ingredient=item.get();
        ingredient.setInStock(!ingredient.isInStock());
        return ingredientItemRepository.save(ingredient);
    }


}
