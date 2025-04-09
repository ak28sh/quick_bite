package com.QuickBite.QuickBite.repository;

import com.QuickBite.QuickBite.model.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {

    public List<FoodCategory> findFoodCategoryByRestaurantId(Long id);
}
