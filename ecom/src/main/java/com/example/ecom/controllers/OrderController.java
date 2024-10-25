package com.example.ecom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecom.model.Order;
import com.example.ecom.service.OrderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder/{userId}/{poductId}")
    public ResponseEntity<Order> addOrder(@RequestBody Order order, @PathVariable Long userId,
            @PathVariable Long poductId) {
        Order newOrder = orderService.addOrder(order, userId, poductId);
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

}
