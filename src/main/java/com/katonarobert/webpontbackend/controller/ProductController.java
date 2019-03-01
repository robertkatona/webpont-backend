package com.katonarobert.webpontbackend.controller;

import com.katonarobert.webpontbackend.model.Product;
import com.katonarobert.webpontbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String index() {
        return "Index Page";
    }

    @GetMapping("/products")
    public List<Product> listAllProducts() {
        return productService.getAll();
    }

    // Add product
    @PostMapping("/add-product")
    public String addProduct(@RequestBody Product newProduct) {
        Product product = new Product(newProduct.getProductName(), newProduct.getProductAmount());
        productService.add(product);
        return "Product: "+ product.getProductName() + " added";
    }

    // Remove product...
    @DeleteMapping(value = "/remove-product/{id}")
    @ResponseBody
    public void removeProduct(@PathVariable String id) {
        productService.remove(Long.valueOf(id));
    }

    // Edit product name...
    @PutMapping("/products/update-name/{id}")
    @ResponseBody
    public void updateProductName(@PathVariable String id, @RequestParam(value = "product-name") String title) {
        productService.update(Long.valueOf(id),title);
    }

    // Edit product amount...
    @PutMapping("/products/update-amount/{id}")
    @ResponseBody
    public void updateProductAmount(@PathVariable String id, @RequestParam(value = "product-amount") int amount) {
        productService.updateAmount(Long.valueOf(id), amount);
    }

    // +/- product amount by one...
    @PutMapping("/products/update-amount/{id}/{increaseAmount}")
    @ResponseBody
    public void increaseProductAmount(@PathVariable String id, @PathVariable String increaseAmount) {
        productService.increaseAmount(Long.valueOf(id), Boolean.valueOf(increaseAmount));
    }

    @GetMapping("/products/amount/{id}")
    @ResponseBody
    public String getProductAmount(@PathVariable String id) {
        return ("Product amount: " + productService.getProduct(Long.valueOf(id)).getProductAmount());
    }
}
