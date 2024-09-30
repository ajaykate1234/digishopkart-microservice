package com.digishopkart.order_service.dto;

import com.digishopkart.order_service.enums.AddressType;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class CustomerAddress {

    private Long id;
    private String fullname;
    private String country;
    private String state;
    private String town;
    private String area;
    private String houseOrBuilding;
    private String landmark;
    private Integer pinCode;
    private String mobile;
    private AddressType addressType;
}
