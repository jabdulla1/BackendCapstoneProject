package com.example.demo.service;

import com.example.demo.client.ProductClient;
import com.example.demo.data.OrderProduct;
import com.example.demo.dto.OrderProductDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ProductDao;
import com.example.demo.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;
    
    @Autowired
    private ProductClient productClient;

    public OrderProduct createOrderProduct(OrderProductDTO orderProductDTO) {
        OrderProduct orderProduct = OrderProduct.builder()
                .productSku(orderProductDTO.getProductSku())
                .productQuantity(orderProductDTO.getProductQuantity())
                .build();
        orderProductRepository.save(orderProduct);
        return orderProduct;
    }

    public List<ProductDao> findByOrderId(UUID orderId) {
        List<OrderProduct> orderProductList = orderProductRepository.findAllByOrderId(orderId);
        
        List<ProductDao>productDaoList =  orderProductList.stream().map((orderProduct) ->{
            
            ProductDao productDao = productClient.getProductDetailsBySku(orderProduct.getProductSku());
            
            return productDao;
            }).collect(Collectors.toList());
        
        return productDaoList;
    }
}
