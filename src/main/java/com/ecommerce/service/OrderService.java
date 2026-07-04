package com.ecommerce.service;


import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.User;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.OrderItemRepository;
import com.ecommerce.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private OrderItemRepository orderItemRepository;

    private CartItemRepository cartItemRepository;

    private CartService cartService;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, CartItemRepository cartItemRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartService = cartService;
    }

    public Order saveOrder(Order order){

        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "Order not found  with id = "+ id
                        ));
    }

    public Page<Order> getOrders(
            int page,
            int size ,
            String sortBy,
            String direction
    ){
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        return orderRepository.findAll(
                PageRequest.of(page , size , sort)
        );
    }

    public Double getOrderTotal(Long id) {

        Order order = getOrderById(id);

        return order.getTotalAmount();
    }

    @Transactional
    public Order placeOrder(Long cartId) {

        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);

        User user = cartService.getCart(cartId).getUser();

        Double totalAmount = 0.0;

        for (CartItem cartItem : cartItems) {
            totalAmount +=
                    cartItem.getProduct().getPrice()
                            * cartItem.getQuantity();
        }

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(totalAmount);
        order.setStatus("PLACED");

        order = orderRepository.save(order);

        for( CartItem  cartItem : cartItems ){

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice( cartItem.getProduct().getPrice());

            orderItemRepository.save(orderItem);
        }
        cartItemRepository.deleteByCartId(cartId);
        return order;
    }

    public Order updateOrderStatus(Long id , String status) {

        Order order = getOrderById(id);

        order.setStatus(status);

        return orderRepository.save(order);
    }
}
