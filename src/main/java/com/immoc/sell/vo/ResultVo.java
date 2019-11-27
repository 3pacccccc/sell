package com.immoc.sell.vo;


import lombok.Data;

import java.io.Serializable;

// http请求返回的最外层对象
@Data
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = -6998800521840788066L;

    private Integer code;

    private String msg;

    private T data;
}
