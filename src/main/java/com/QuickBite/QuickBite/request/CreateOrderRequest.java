package com.QuickBite.QuickBite.request;

import com.QuickBite.QuickBite.model.Address;
import lombok.Data;

@Data
public class CreateOrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;
}
