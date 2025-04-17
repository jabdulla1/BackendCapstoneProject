package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
public class ProductController {//localhost:8081/api/v1/inventory
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products/all")
	public List<ProductDao> getAllproducts() {
		
		return productService.getAllproducts();
	}
	
	@PostMapping("/products/add")
	public ProductDao addProduct(@RequestBody ProductDao productDao) {
		return productService.addProduct(productDao);
	}
	
	@PutMapping("/products/update/{sku}")
	public ProductDao updateProduct(@PathVariable int sku, @RequestBody ProductDao productDao) {
		return productService.updateProduct(sku, productDao);
	}
	
	@DeleteMapping("/products/{id}")
	public ProductDao deleteProduct(@PathVariable int id) {
		return productService.deleteProduct(id);
	}	
	
	@DeleteMapping("/products/sku/{sku}")
	public ProductDao deleteProductBySku(@PathVariable int sku) {
		return productService.deleteProductBySku(sku);
	}
	
	@GetMapping("/products/{id}")
	public ProductDao getProductDetails(@PathVariable int id){
		return productService.getProductDetails(id);
	}
	
	@GetMapping("/products/sku/{sku}")
	public ProductDao getProductDetailsBySku(@PathVariable int sku){
		return productService.getProductDetailsBySku(sku);
	}
	
	@GetMapping("/alerts")
	public List<InventoryStockDao> getAlerts() {	
		return productService.getAlerts() ;
	}
	
	@PostMapping("/orders/place")
	public String sendOrder(@RequestBody OrderRequestDTO orderRequestDTO) {	
		return productService.sendOrder(orderRequestDTO);
	}
}
