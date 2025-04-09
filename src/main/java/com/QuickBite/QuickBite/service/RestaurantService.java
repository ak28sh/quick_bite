package com.QuickBite.QuickBite.service;

import com.QuickBite.QuickBite.Exception.RestaurantException;
import com.QuickBite.QuickBite.dto.RestaurantDto;
import com.QuickBite.QuickBite.model.Restaurant;
import com.QuickBite.QuickBite.model.User;
import com.QuickBite.QuickBite.request.CreateRestaurantRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws RestaurantException;

    public void deleteRestaurant(Long restaurantId) throws RestaurantException;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long restaurantId) throws RestaurantException;

    public Restaurant getRestaurantByUserId(Long userId) throws RestaurantException;

    public RestaurantDto addToFavorites(Long restaurantId, User user) throws RestaurantException;
    public void removeFromFavorite(Long restaurantId, User user) throws RestaurantException;

    public Restaurant updateRestaurantStatus(Long restaurantId) throws RestaurantException;


}
