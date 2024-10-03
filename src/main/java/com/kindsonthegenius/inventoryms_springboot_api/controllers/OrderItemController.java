package com.kindsonthegenius.inventoryms_springboot_api.controllers;

import com.kindsonthegenius.inventoryms_springboot_api.models.OrderItem;
import com.kindsonthegenius.inventoryms_springboot_api.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderItemController {

    private OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;

    }

    @GetMapping("/orderItems")
    public List<OrderItem> getOrderItemes(){
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/orderItem/{id}")
    public OrderItem getOrderItem(@PathVariable("id") Long id){
        return orderItemService.getOrderItemById(id);
    }

    @PutMapping("/orderItem/{id}")
    public OrderItem updateOrderItem(@RequestBody() OrderItem orderItem, @PathVariable("id") Long id){
        return orderItemService.save(orderItem);
    }

    @PostMapping("/orderItems")
    public OrderItem addNew(@RequestBody() OrderItem orderItem){
        return orderItemService.save(orderItem);
    }

    @DeleteMapping("/orderItem/{id}")
    public void deleteOrderItem(@PathVariable("id") Long id){
        orderItemService.deleteOrderItem(id);
    }
}
