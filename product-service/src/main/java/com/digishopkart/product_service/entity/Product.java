package com.digishopkart.product_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Lob
    private byte[] productImage;

    private LocalDate manufacturingDate;
    private LocalDate expiryDate;
    private Boolean isExpired;

}
