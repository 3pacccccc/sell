package com.immoc.sell.service;

import com.immoc.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);

    List<ProductInfo> findUpAll(); // 查询所有在架商品列表

    Page<ProductInfo> findAll(Pageable pageable); // 查看所有商品；

    ProductInfo save(ProductInfo productInfo);

    // todo 加库存，减库存
}
