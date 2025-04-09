package com.QuickBite.QuickBite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.QuickBite.QuickBite.model.IngredientItem;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientItemRepository extends JpaRepository<IngredientItem, Long> {


    List<IngredientItem> findByRestaurantId(Long id);
    @Query("SELECT e FROM IngredientItem e "
            + "WHERE e.restaurant.id = :restaurantId "
            + "AND lower(e.name) = lower(:name)"
            + "AND e.ingredientCategory.name = :categoryName")
    public IngredientItem findByRestaurantIdAndNameIngoreCase(
            @Param("restaurantId") Long restaurantId,
            @Param("name") String name,
            @Param("categoryName") String categoryName);
}
