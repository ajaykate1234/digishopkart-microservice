package com.digishopkart.order_service.dto;

import lombok.Data;

@Data
public class Varient {

    private Long id;
    private String name;
    private String value;
    private Boolean availability;

}
