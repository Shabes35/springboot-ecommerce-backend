package com.ecommerce.controller;


import com.ecommerce.entity.CartItem;
import com.ecommerce.service.CartItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartitems")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public CartItem addCartItem(@RequestBody CartItem cartItem){
        return cartItemService.addItem(cartItem);
    }

    @GetMapping
    public List<CartItem> allCartItems(){
        return  cartItemService.getAllCartItems();
    }

    @GetMapping("/{id}")
    public CartItem getCartItemById(@PathVariable long id){
        return cartItemService.getCartItemById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCartItemById(@PathVariable long id){
        cartItemService.deleteCartItemById(id);
    }
}
