package com.immoc.sell.controller;

import com.immoc.sell.dataobject.ProductInfo;
import com.immoc.sell.enums.ProductStatusEnum;
import com.immoc.sell.exception.SellException;
import com.immoc.sell.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId, Map<String, Object> map) {
        try {
            ProductInfo productInfo = productService.onSale(productId);
            if (productInfo.getProductStatus().equals(ProductStatusEnum.UP.getCode())) {
                // 上架成功
                map.put("msg", "上架成功");
                map.put("url", "/sell/seller/product/list");
                return new ModelAndView("common/success", map);
            }
        } catch (SellException e) {
            log.error("【商品上架】发生错误:{}", e);
        }
        map.put("msg", "商品上架错误");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/error", map);
    }

    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId, Map<String, Object> map) {
        try {
            ProductInfo productInfo = productService.offSale(productId);
            if (productInfo.getProductStatus().equals(ProductStatusEnum.DOWN.getCode())) {
                // 上架成功
                map.put("msg", "下架成功");
                map.put("url", "/sell/seller/product/list");
                return new ModelAndView("common/success", map);
            }
        } catch (SellException e) {
            log.error("【商品下架】发生错误:{}", e);
        }
        map.put("msg", "商品下架错误");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/error", map);
    }
}
