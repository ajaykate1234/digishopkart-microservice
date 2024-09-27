package com.digishopkart.customer_service.entity;

import com.digishopkart.customer_service.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "digi_customer")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;
    private String mobile;
    private Boolean status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerAddress_id")
    List<CustomerAddress> customerAddressList =new ArrayList<>();

}
