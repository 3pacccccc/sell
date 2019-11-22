package com.immoc.sell.service;

import com.immoc.sell.dto.OrderDTO;

public interface PayService {

    void create(OrderDTO orderDTO);
}
