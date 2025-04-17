package com.example.demo.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderProductDTO {
    private UUID orderId;
    private Integer productSku;
    private Integer productQuantity;
}
