package com.example.demo.client;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ProductDao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ProductManagement", url = "localhost:8091/api/v1/inventory")
public interface ProductClient {

    @PostMapping("/products/add")
    void addProduct(@RequestBody ProductDao product);

    @PutMapping("/update/{id}")
    void updateProduct(@RequestBody ProductDao product, @PathVariable int id);

    @GetMapping("/products/{id}")
    ProductDao getProductDetails(@PathVariable int id);
    
	@GetMapping("/products/sku/{sku}")
	ProductDao getProductDetailsBySku(@PathVariable int sku);
}
