package com.QuickBite.QuickBite.service;

import com.QuickBite.QuickBite.model.OrderItem;
import com.QuickBite.QuickBite.request.CreateOrderItemRequest;
import org.springframework.stereotype.Service;

@Service
public interface OrderItemService {
    public OrderItem ceateOrderItem(CreateOrderItemRequest req) throws Exception;
}


