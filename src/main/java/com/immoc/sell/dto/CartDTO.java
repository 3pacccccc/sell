package com.immoc.sell.dto;

import lombok.Data;

@Data
public class CartDTO {
    // 购物车

    private String productId; // 商品ID

    private Integer productQuantity; // 商品数量

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
