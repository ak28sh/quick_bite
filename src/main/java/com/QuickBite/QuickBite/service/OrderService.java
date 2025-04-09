package com.QuickBite.QuickBite.service;

import com.QuickBite.QuickBite.Exception.CartException;
import com.QuickBite.QuickBite.Exception.OrderException;
import com.QuickBite.QuickBite.Exception.RestaurantException;
import com.QuickBite.QuickBite.Exception.UserException;
import com.QuickBite.QuickBite.model.Order;
import com.QuickBite.QuickBite.model.User;
import com.QuickBite.QuickBite.request.CreateOrderRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    public Order createOrder(CreateOrderRequest order, User user) throws UserException, RestaurantException, CartException;

    public Order updateOrder(Long orderId, String orderStatus) throws OrderException;

    public void cancelOrder(Long orderId) throws OrderException;

    public List<Order> getUserOrders(Long userId) throws OrderException;

    public List<Order> getOrdersOfRestaurant(Long restaurantId,String orderStatus) throws OrderException, RestaurantException;

    public Order findOrderById(Long orderId) throws OrderException;
}
