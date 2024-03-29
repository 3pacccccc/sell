package com.immoc.sell.controller;

import com.immoc.sell.dataobject.ProductCategory;
import com.immoc.sell.dataobject.ProductInfo;
import com.immoc.sell.service.CategoryService;
import com.immoc.sell.service.ProductService;
import com.immoc.sell.vo.ProductInfoVo;
import com.immoc.sell.vo.ProductVo;
import com.immoc.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.immoc.sell.utils.ResultVoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
//    @Cacheable(cacheNames = "product", key = "123")  // 对这个函数使用缓存
//    @Cacheable(cacheNames = "product", key = "#productId", condition = "#productId.length() > 3". unless = "#result.getCode !=0 ")
//    key可以动态配置：SPEL表达式, condition表达式后面成立的时候才会缓存
//    unless表示当ResultVo的code不为0的时候，才进行缓存
    public ResultVo list() {
        // 查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        // 查询所有商品所属类目
//        List<Integer> categoryList = new ArrayList<>();
        //传统方法
//        for (ProductInfo productInfo : productInfoList){
//            categoryList.add(productInfo.getCategoryType());
//        }
        // 精简方法(java8, lambda)
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        // 数据拼装
        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryType(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);  // 直接将productInfo的字段拷贝到productInfoVo
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }

        return ResultVoUtil.success(productVoList);
    }
}
