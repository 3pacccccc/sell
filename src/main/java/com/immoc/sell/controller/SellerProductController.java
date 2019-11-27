package com.immoc.sell.controller;

import com.immoc.sell.dataobject.ProductCategory;
import com.immoc.sell.dataobject.ProductInfo;
import com.immoc.sell.enums.ProductStatusEnum;
import com.immoc.sell.exception.SellException;
import com.immoc.sell.form.ProductForm;
import com.immoc.sell.service.CategoryService;
import com.immoc.sell.service.ProductService;
import com.immoc.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
@Slf4j
//@CacheConfig(cacheNames = "product")  如果在class上面添加了这个，函数下面@Cacheable(cacheNames = "product", key = "123")中的cacheNames = "product"可以省去
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product", key = "123")
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


    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            // 如果productId不为空，表示更新商品操作
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }

        // 查询所有的类目
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("productCategoryList", productCategoryList);

        return new ModelAndView("product/index", map);
    }


    @PostMapping("/save")
//    @CachePut(cacheNames = "product", key = "123")  //cacheput每次访问会执行方法下面的函数。结果存入到redis. cacheable则直接访问redis得到结果。不会访问函数下面的代码
    @CacheEvict(cacheNames = "product", key = "123")
    // CacheEvict会在删除redis对应的缓存， key如果不填默认为方法的请求参数。此处为@Valid ProductForm productForm，BindingResult bindingResult，Map<String, Object> map
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");

            //todo 错误栏标红，旁边写出错误信息。其他信息不变
//            String field = bindingResult.getFieldError().getField();
//            String msg = bindingResult.getFieldError().getDefaultMessage();
//            map.put("url", "/sell/seller/product/index");
//            map.put("field",field);
//            map.put("msg", msg);
//            map.put("productForm", productForm);

            return new ModelAndView("common/error", map);
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            String productId = productForm.getProductId();
            if (StringUtils.isEmpty(productId)) {
                //当新增商品的时
                productForm.setProductId(KeyUtil.genUniqueKey());
            } else {
                productInfo = productService.findOne(productForm.getProductId());
            }

            BeanUtils.copyProperties(productForm, productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

}
