package com.QuickBite.QuickBite.service;

import com.QuickBite.QuickBite.Exception.CartException;
import com.QuickBite.QuickBite.Exception.CartItemException;
import com.QuickBite.QuickBite.Exception.FoodException;
import com.QuickBite.QuickBite.Exception.UserException;
import com.QuickBite.QuickBite.model.Cart;
import com.QuickBite.QuickBite.model.CartItem;
import com.QuickBite.QuickBite.request.AddCartItemRequest;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws UserException, FoodException, CartException, CartItemException;

    public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws CartItemException;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, CartException, CartItemException;

    public Long calculateCartTotals(Cart cart) throws UserException;

    public Cart findCartById(Long id) throws CartException;

    public Cart findCartByUserId(Long userId) throws CartException, UserException;

    public Cart clearCart(Long userId) throws CartException, UserException;

}
