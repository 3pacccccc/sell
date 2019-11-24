package com.immoc.sell.service;

import com.immoc.sell.dataobject.ProductInfo;
import com.immoc.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);

    List<ProductInfo> findUpAll(); // 查询所有在架商品列表

    Page<ProductInfo> findAll(Pageable pageable); // 查看所有商品；

    ProductInfo save(ProductInfo productInfo);

    void increaseStock(List<CartDTO> cartDTOList);  //加库存

    void decreaseStock(List<CartDTO> cartDTOList); //减库存

    ProductInfo onSale(String productId); //商品上架

    ProductInfo offSale(String productId); //商品下架
}
