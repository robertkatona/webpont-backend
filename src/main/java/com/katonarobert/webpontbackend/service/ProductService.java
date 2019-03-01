package com.katonarobert.webpontbackend.service;

import com.katonarobert.webpontbackend.model.Product;
import com.katonarobert.webpontbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.getOne(id);
    }

    public void add(Product product) {
        productRepository.saveAndFlush(product);
    }

    public void remove(Long productId) {
        productRepository.deleteById(productId);
    }

    public void update(Long productId, String editedName) {
        Product editProduct = productRepository.getOne(productId);
        editProduct.setProductName(editedName);
        productRepository.saveAndFlush(editProduct);
    }


    public void updateAmount(Long productId, int amount) {
        Product editProduct = productRepository.getOne(productId);
        editProduct.setProductAmount(amount);
        productRepository.saveAndFlush(editProduct);
    }

    public void increaseAmount(Long productId, boolean increaseAmount) {
        Product editProduct = productRepository.getOne(productId);
        editProduct.increaseProductAmount(increaseAmount);
        productRepository.saveAndFlush(editProduct);
    }
}
