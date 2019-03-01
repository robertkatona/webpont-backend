package com.katonarobert.webpontbackend.repository;

import com.katonarobert.webpontbackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
