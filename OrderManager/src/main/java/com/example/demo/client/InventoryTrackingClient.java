package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.data.InventoryStockDao;


@FeignClient(name="InventoryTracking",url="localhost:9090/stockapi")
public interface InventoryTrackingClient {
	//adding product to the inventory
	@PostMapping("/add")
	public InventoryStockDao addToInventory(InventoryStockDao  inventoryStockDao);
	  
	@PutMapping("/sku/{sku}")
	public InventoryStockDao updateStockBySku(@PathVariable int sku, @RequestBody InventoryStockDao inventoryStockDao);	
	
	@PostMapping("/decrease/sku/{sku}")
    public InventoryStockDao decreaseStockBySku(@PathVariable int sku, @RequestBody int quantity);
	      
}
