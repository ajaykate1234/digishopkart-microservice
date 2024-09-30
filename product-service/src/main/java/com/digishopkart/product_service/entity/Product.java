package com.digishopkart.product_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;
    private String brand;
    private Double productPrice;
    private Boolean availability;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "varient_id")
    private List<Varient> varientList =new ArrayList<>();
}
