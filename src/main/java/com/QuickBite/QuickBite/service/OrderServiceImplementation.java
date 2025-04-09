package com.QuickBite.QuickBite.service;

import com.QuickBite.QuickBite.Exception.CartException;
import com.QuickBite.QuickBite.Exception.OrderException;
import com.QuickBite.QuickBite.Exception.RestaurantException;
import com.QuickBite.QuickBite.Exception.UserException;
import com.QuickBite.QuickBite.model.*;
import com.QuickBite.QuickBite.repository.*;
import com.QuickBite.QuickBite.request.CreateOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation implements OrderService{

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;

    private OrderItemRepository orderItemRepository;

    @Override
    public Order createOrder(CreateOrderRequest req, User user) throws UserException, RestaurantException, CartException {

        Address shipAddress = req.getDeliveryAddress();

        Address savedAddress = addressRepository.save(shipAddress);

        if(!user.getAddress().contains(savedAddress)){
            user.getAddress().add(savedAddress);
            userRepository.save(user);
        }

        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());

        Order newOrder = new Order();
        newOrder.setCustomer(user);
        newOrder.setRestaurant(restaurant);
        newOrder.setCreatedAt(new Date());
        newOrder.setOrderStatus("PENDING");
        newOrder.setDeliveryAddress(savedAddress);

        Cart cart = cartService.findCartByUserId(user.getId());

        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
//            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuatity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(orderItem);

        }

        Long totalPrice = cartService.calculateCartTotals(cart);

        newOrder.setItems(orderItems);
        newOrder.setTotalPrice(totalPrice);

        Order savedOrder = orderRepository.save(newOrder);
        restaurant.getOrders().add(savedOrder);
        return newOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws OrderException {
        Order order=findOrderById(orderId);
        if(orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED")
                || orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING")
        ) {

            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        else throw new OrderException("Please Select A Valid Order Status");
    }

    @Override
    public void cancelOrder(Long orderId) throws OrderException {
        Order order =findOrderById(orderId);
        if(order==null) {
            throw new OrderException("Order not found with the id "+orderId);
        }

        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUserOrders(Long userId) throws OrderException {
        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getOrdersOfRestaurant(Long restaurantId, String orderStatus) throws OrderException, RestaurantException {
        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);

        if(orderStatus!=null) {
            orders = orders.stream()
                    .filter(order->order.getOrderStatus().equals(orderStatus))
                    .collect(Collectors.toList());
        }

        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {

        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()) return order.get();

        throw new OrderException("Order not found with the id "+orderId);
    }
}
