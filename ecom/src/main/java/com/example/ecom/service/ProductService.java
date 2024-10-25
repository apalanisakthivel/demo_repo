package com.example.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecom.model.Product;
import com.example.ecom.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // add product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // get product by id
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // update product
    public Product updateProduct(Product product, Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        return productRepository.save(existingProduct);
    }

    // delete product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
