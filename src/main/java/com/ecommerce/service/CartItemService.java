package com.ecommerce.service;


import com.ecommerce.entity.CartItem;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository ;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem addItem(CartItem cartItem){
        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public  CartItem getCartItemById(long id){
        return cartItemRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "CartItem not found with id = "+ id
                        ));
    }

    public void deleteCartItemById(long id){

        cartItemRepository.deleteById(id);
    }

    public List<CartItem> getCartItems(Long id) {
       return  cartItemRepository.findByCartId(id);
    }

    public Double totalPrice(Long id) {
       List<CartItem>  cartItems = cartItemRepository.findByCartId(id);

       double total =0;
       for(CartItem cartItem : cartItems){
           total += cartItem.getProduct().getPrice()  * cartItem.getQuantity();
       }
       return total;
    }
}
