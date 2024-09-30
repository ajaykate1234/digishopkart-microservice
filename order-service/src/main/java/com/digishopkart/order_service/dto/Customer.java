package com.digishopkart.order_service.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
@Data
public class Customer {

    private Long id;
    private String name;
    private String gender;
    private String email;
    private String mobile;
    private Boolean status;
    private List<CustomerAddress> customerAddressList =new ArrayList<>();

}