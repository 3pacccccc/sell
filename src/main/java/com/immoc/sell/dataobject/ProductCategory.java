package com.immoc.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity // 数据库映射到对象
@DynamicUpdate // 动态更新时间
@Data // 可以不写get，set方法
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer categoryId;  // 类目ID

    private String categoryName;  // 类目名字

    private Integer categoryType; // 类目编号

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
