package com.immoc.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class SellerInfo {

    @Id
    private String id;

    private String userName;

    private String password;

    private String openid;
}
