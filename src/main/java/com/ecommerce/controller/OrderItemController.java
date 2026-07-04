package com.ecommerce.controller;


import com.ecommerce.entity.OrderItem;
import com.ecommerce.service.OrderItemService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orderitems")
public class OrderItemController {

    private  final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public Page<OrderItem> getOrderItems(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue =  "2") int size,
            @RequestParam (defaultValue =  "id" ) String sortBy,
            @RequestParam (defaultValue = "desc") String direction
    ){
        return orderItemService.getOrderItems(page , size, sortBy , direction);
    }

    @GetMapping("/{id}")
    public OrderItem orderItem(@PathVariable Long id){
        return orderItemService.getOrderItemById(id);
    }

    @PostMapping
    public OrderItem addOrderItem(@RequestBody OrderItem orderItem){
        return  orderItemService.addOrderItem(orderItem);
    }


}
