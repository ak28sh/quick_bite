package com.QuickBite.QuickBite.repository;

import com.QuickBite.QuickBite.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByRestaurantId(Long restaurantId);

//    @Query("SELECT f FROM Food f WHERE f.name LIKE CONCAT('%', :keyword, '%') OR f.foodCategory.name LIKE CONCAT('%', :keyword, '%') AND f.restaurant != null")

//    List<Food> searchFoodByNameOrCategory(@Param("keyword") String keyword);

}
