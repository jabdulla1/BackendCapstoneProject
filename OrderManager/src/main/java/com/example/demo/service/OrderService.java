package com.example.demo.service;


import com.example.demo.client.InventoryTrackingClient;
import com.example.demo.client.ProductClient;
import com.example.demo.client.SupplierClient;
import com.example.demo.data.InventoryStockDao;
import com.example.demo.data.Order;
import com.example.demo.data.OrderProduct;
import com.example.demo.dto.AlertDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderRequestDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ProductDao;
import com.example.demo.dto.SupplierDTO;
import com.example.demo.repository.AlertRepository;
import com.example.demo.repository.OrderProductRepository;
import com.example.demo.repository.OrderRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    SupplierClient supplierClient;
    @Autowired
    ProductClient productClient;
    @Autowired
    private AlertService alertService;
    @Autowired
    private InventoryTrackingClient inventoryTrackingClient;

    @Transactional
    public OrderDTO createOrder(OrderRequestDTO OrderRequest) {
        Order order = Order.builder()
                .supplierId(OrderRequest.getSupplier_id())
                .build();

         final Order order2 = orderRepository.save(order);

        HashMap<Integer, Integer> productSkusAndQuantities = OrderRequest.getProductSkusAndQuantities();

        //Order finalOrder = order;
        productSkusAndQuantities.forEach((sku, quantity) -> {
        	
        	ProductDao productDao = productClient.getProductDetailsBySku(sku);
        	//OrderProduct orderProduct2 = orderProductRepository.findByOrderId(finalOrder.getOrderId());
        	
            OrderProduct orderProduct = OrderProduct.builder()
                    .orderId(order2.getOrderId())
                    .productSku(productDao.getSku())
                    .productName(productDao.getName())
                    .productQuantity(quantity)
                    .build();
            orderProductRepository.save(orderProduct);
            
            
            inventoryTrackingClient.decreaseStockBySku(sku, quantity);
    		
        });

        
        return convertOrderToOrderDTO(order2);
    }

    public OrderDTO getOrderById(UUID orderId) {
        Order order = orderRepository.getReferenceById(orderId);
        return convertOrderToOrderDTO(order);
    }

    public List<OrderDTO> getAllOrder() {
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream().map(this::convertOrderToOrderDTO).collect(Collectors.toList());
    }

    public OrderDTO updateOrder(UUID orderId, OrderRequestDTO OrderRequest) {

        
    	Order order = Order.builder()
    			.orderId(orderId)
                .supplierId(OrderRequest.getSupplier_id())
                .build();

         final Order order2 = orderRepository.save(order);

        LinkedHashMap<Integer, Integer> productSkusAndQuantities = OrderRequest.getProductSkusAndQuantities();
        Set<OrderProduct> orderProductSet2 = new LinkedHashSet<OrderProduct>();
        List<OrderProduct> orderProducts = orderProductRepository.findAllByOrderId(order2.getOrderId());
        //Order finalOrder = order;
        productSkusAndQuantities.forEach((sku, quantity) -> {
        	 ProductDao productDao = productClient.getProductDetailsBySku(sku);
        	 
        	 OrderProduct orderProduct = OrderProduct.builder()
             		//.id(orderProductS.getId())
                    .orderId(order2.getOrderId())
                    .productSku(productDao.getSku())
                    .productName(productDao.getName())
                    .productQuantity(quantity)
                    .build();
        	
        	 
        	 Set<OrderProduct> orderProductSet = orderProducts.stream().map((orderProductS)->{
        		
            	//OrderProduct orderProduct2 = orderProductRepository.findByOrderId(order2.getOrderId());
            	
                orderProduct.setId(orderProductS.getId());
                		                        
                return orderProduct;
                
        	 }).collect(Collectors.toSet());
        	 
        	 
        	 orderProductRepository.saveAll(orderProductSet);
        	 
        	 int orderListSize = orderProducts.size();
        	 int mapKeySize = productSkusAndQuantities.size();
        	 System.out.println("orderProducts size: "+orderListSize);
        	 System.out.println("map key size: "+mapKeySize);
        	 System.out.println("set orderProductSet: "+ orderProductSet.size());

        	 if(mapKeySize > orderListSize && orderProductSet.contains(orderProduct) == false) {
//        		 Set<OrderProduct> orderProductSet2 = new LinkedHashSet<OrderProduct>();
        		 
        		 orderProductSet2.add(orderProduct);
        		 System.out.println("inside the orderProductSet2: "+orderProductSet2.size());
        		 //orderProductRepository.save(orderProduct);
        	 }
        	 
             
             inventoryTrackingClient.decreaseStockBySku(sku, quantity);
        	
        });

        orderProductRepository.saveAll(orderProductSet2);
        
        return convertOrderToOrderDTO(order2);
    	
    
    }
    @Transactional
    public OrderDTO deleteOrder(UUID orderId) {

    	Order order = orderRepository.findById(orderId).orElse(null);
   
    	OrderDTO orderDTO = convertOrderToOrderDTO(order);
    	
        List<OrderProduct> orderProducts = orderProductRepository.findAllByOrderId(order.getOrderId());
        orderProductRepository.deleteAll(orderProducts);

        
        orderRepository.delete(order);
        
        return orderDTO;
    }

    private OrderDTO convertOrderToOrderDTO(Order finalOrder) {
        List<OrderProduct> orderProducts = orderProductRepository.findAllByOrderId(finalOrder.getOrderId());

        
        List<ProductDTO> productDTOList = orderProducts.stream().map((orderProduct) ->{ 
        
        	ProductDao productDao = productClient.getProductDetailsBySku(orderProduct.getProductSku());
        	
        	//ProductDTO productDTO = new ProductDTO(orderProduct.getProductId(), orderProduct.getProductQuantity(), orderProduct.getProductName());  
        	ProductDTO productDTO =	ProductDTO.builder()
                .productSku(orderProduct.getProductSku())
                .productQuantity(orderProduct.getProductQuantity())
                .productName(orderProduct.getProductName())
                .build();
                
        	
        	AlertDTO alertDTO = AlertDTO.builder()
        			.productSKU(orderProduct.getProductSku())
        			.quantity(orderProduct.getProductQuantity()).build();
        			
        	alertService.createAlert(alertDTO);
        	
        	return productDTO;
        	
        }).collect(Collectors.toList());

        SupplierDTO supplierDTO = supplierClient.getSupplierById(finalOrder.getSupplierId()); //SupplierDTO.builder().id(finalOrder.getSupplierId()).build();

        return OrderDTO.builder()
                .orderId(finalOrder.getOrderId())
                .productList(productDTOList)
                .supplier(supplierDTO)
                .build();
    }

}
