package com.QuickBite.QuickBite.controller;

import com.QuickBite.QuickBite.Exception.CartException;
import com.QuickBite.QuickBite.Exception.OrderException;
import com.QuickBite.QuickBite.Exception.RestaurantException;
import com.QuickBite.QuickBite.Exception.UserException;
import com.QuickBite.QuickBite.model.Order;
import com.QuickBite.QuickBite.model.PaymentResponse;
import com.QuickBite.QuickBite.model.User;
import com.QuickBite.QuickBite.request.CreateOrderRequest;
import com.QuickBite.QuickBite.service.OrderService;
import com.QuickBite.QuickBite.service.UserService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Order>  createOrder(@RequestBody CreateOrderRequest order,
                                                        @RequestHeader("Authorization") String jwt)
            throws UserException, RestaurantException,
            CartException,
            StripeException,
            OrderException {
        User user=userService.findUserByJwtToken(jwt);
        System.out.println("req user "+user.getEmail());
        if(order!=null) {
            Order res = orderService.createOrder(order,user);
            return ResponseEntity.ok(res);

        }else throw new OrderException("Please provide valid request body");

    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getAllUserOrders(@RequestHeader("Authorization") String jwt) throws OrderException, UserException{

        User user=userService.findUserByJwtToken(jwt);

        if(user.getId()!=null) {
            List<Order> userOrders = orderService.getUserOrders(user.getId());
            return ResponseEntity.ok(userOrders);
        }else {
            return new ResponseEntity<List<Order>>(HttpStatus.BAD_REQUEST);
        }
    }
}
