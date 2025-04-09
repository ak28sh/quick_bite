package com.QuickBite.QuickBite.service;

import com.QuickBite.QuickBite.model.OrderItem;
import com.QuickBite.QuickBite.request.CreateOrderItemRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImplementation implements OrderItemService{

    @Override
    public OrderItem ceateOrderItem(CreateOrderItemRequest req) {
        System.out.print("Create Order Request");
        System.out.print(req);
        OrderItem newOrderItem = new OrderItem();
        newOrderItem.setFood(req.getFood());
        newOrderItem.setQuatity(req.getQuatity());
	    newOrderItem.setTotalPrice(req.getTotalPrice());
        return newOrderItem;
    }
}
