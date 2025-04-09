package com.QuickBite.QuickBite.controller;


import java.util.List;

import com.QuickBite.QuickBite.request.CreateIngredientCategoryRequest;
import com.QuickBite.QuickBite.request.CreateIngredientRequest;
import com.QuickBite.QuickBite.request.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.QuickBite.QuickBite.model.IngredientCategory;
import com.QuickBite.QuickBite.model.IngredientItem;
import com.QuickBite.QuickBite.service.IngredientService;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody CreateIngredientCategoryRequest req) throws Exception{
        IngredientCategory items=ingredientService.createIngredientsCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(items,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<IngredientItem> createIngredient(
            @RequestBody CreateIngredientRequest req) throws Exception{

        IngredientItem item=ingredientService.createIngredientsItem(req.getRestaurantId(),req.getName(),req.getIngredientCategoryId());
        return new ResponseEntity<>(item,HttpStatus.OK);
    }

    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientItem> updateStoke(@PathVariable Long id) throws Exception{
        IngredientItem item=ingredientService.updateStoke(id);
        return new ResponseEntity<IngredientItem>(item,HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientItem>> restaurantsIngredient(
            @PathVariable Long id) throws Exception{
        List<IngredientItem> items=ingredientService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(items,HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> restaurantsIngredientCategory(
            @PathVariable Long id) throws Exception{
        List<IngredientCategory> items=ingredientService.findIngredientsCategoryByRestaurantId(id);
        return new ResponseEntity<>(items,HttpStatus.OK);
    }

}
