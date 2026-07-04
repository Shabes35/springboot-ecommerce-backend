package com.ecommerce.service;

import com.ecommerce.entity.Cart;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private  final CartRepository cartRepository ;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart addCart(Cart cart){
        return cartRepository.save(cart);
    }

    public Cart getCart(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "Cart not found with id = "+ id
                        )
                        );
    }
}
