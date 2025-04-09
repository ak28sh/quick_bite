package com.QuickBite.QuickBite.request;

import com.QuickBite.QuickBite.model.Food;
import lombok.Data;

@Data
public class CreateOrderItemRequest {

    private Food food;

    private int quatity;

    private Long totalPrice;
}
