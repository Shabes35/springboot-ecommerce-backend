package com.ecommerce.controller;


import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.service.OrderItemService;
import com.ecommerce.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    private OrderItemService orderItemService;

    public OrderController(OrderItemService orderItemService, OrderService orderService) {
        this.orderItemService = orderItemService;
        this.orderService = orderService;
    }

    @PostMapping
    public Order addOrder(@RequestBody Order order){
        return orderService.saveOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @GetMapping
    public Page<Order> getAllOrders(
            @RequestParam (defaultValue =  "0") int page ,
            @RequestParam (defaultValue = "2") int size,
            @RequestParam (defaultValue = "id")String sortBy,
            @RequestParam (defaultValue = "asc")String direction
    ){
         return orderService.getOrders(page , size ,sortBy, direction);
    }

    @GetMapping("/{id}/items")
    public List<OrderItem> getOrderItemsByOrderId(@PathVariable Long id){
        return orderItemService.getOrderItemsByOrderId(id);
    }

    @GetMapping("/{id}/total")
    public Double getOrderTotal(@PathVariable Long id){
        return orderService.getOrderTotal(id);
    }

    @PostMapping("/place/{cartId}")
    public Order placeOrder(@PathVariable Long cartId){
        return orderService.placeOrder(cartId);
    }

    @PatchMapping("/{id}/status")
    public Order updateOrderStatus(@PathVariable Long id ,
                                   @RequestBody Map<String ,String> body){
        return orderService.updateOrderStatus(id ,body.get("status"));
    }

}
