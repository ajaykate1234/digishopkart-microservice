package com.digishopkart.customer_service.entity;

import com.digishopkart.customer_service.enums.AddressType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "digi_customerAddress")
@Data
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Enumerated(EnumType.STRING)
    private AddressType addressType;
}
