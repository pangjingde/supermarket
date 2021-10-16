package com.southwind.mmall002.productCategoryVO;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
public class CartVO {
    private Integer id;
    private Float cost;
    private Integer productId;
    private Integer quantity;
    private String name;
    private Float price;
    private String fileName;
    private  Integer stock;


}


