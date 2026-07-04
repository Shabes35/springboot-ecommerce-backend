package com.ecommerce.controller;


import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import com.ecommerce.service.CartItemService;
import com.ecommerce.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private  final CartService cartService;

    private final CartItemService cartItemService;

    public CartController(CartService cartService, CartItemService cartItemService) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id){
        return cartService.getCart(id);
    }

    @PostMapping
    public Cart addCart(@RequestBody Cart cart){
        return cartService.addCart(cart);
    }

    @GetMapping("/{id}/items")
    public List<CartItem> getCartItemsById(@PathVariable Long id){
        return cartItemService.getCartItems(id);
    }

    @GetMapping("/{id}/total")
    public double  getCartItemsTotal ( @PathVariable Long id){
        return cartItemService.totalPrice(id);
    }

}
