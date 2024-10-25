package com.example.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecom.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
