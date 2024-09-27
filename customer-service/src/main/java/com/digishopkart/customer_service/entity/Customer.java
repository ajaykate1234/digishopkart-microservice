package com.digishopkart.customer_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "digi_customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String gender;
    private String email;
    private String mobile;
    private Boolean status;

}
