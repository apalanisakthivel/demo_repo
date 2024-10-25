package com.example.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecom.model.Order;
import com.example.ecom.model.Product;
import com.example.ecom.model.User;
import com.example.ecom.repository.OrderRepository;
import com.example.ecom.repository.ProductRepository;
import com.example.ecom.repository.UserRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    // add Orders
    public Order addOrder(Order order, Long userId, Long poductId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(poductId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        order.setUser(user);
        order.setProduct(product);
        return orderRepository.save(order);
    }

    // get Orders
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
