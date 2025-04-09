package com.QuickBite.QuickBite.service;

import com.QuickBite.QuickBite.model.CartItem;
import org.springframework.stereotype.Service;

@Service
public interface CartItemService {

    public CartItem createCartItem(CartItem item);
}
