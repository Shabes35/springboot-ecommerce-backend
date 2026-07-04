package com.ecommerce.service;


import com.ecommerce.entity.OrderItem;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.OrderItemRepository;
import com.ecommerce.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public Page <OrderItem>  getOrderItems(
            int page,
            int size,
            String sortBy,
            String direction
    ){
        Sort sort = direction.equalsIgnoreCase("Desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        return orderItemRepository.findAll(
                PageRequest.of(page ,size, sort)
        );
    }

    public OrderItem getOrderItemById(Long id) {

        return orderItemRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(
                        "OrderItem not found with id "+ id
                ));
    }

    public OrderItem addOrderItem(OrderItem orderItem){
        return  orderItemRepository.save(orderItem);
    }

    public List<OrderItem> getOrderItemsByOrderId(Long id) {
        return orderItemRepository.getOrderItemsByOrderId(id);
    }
}
