package com.digishopkart.order_service.dto;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Product {

    private Long id;
    private String productName;
    private String brand;
    private Double productPrice;
    private Boolean availability;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "varient_id")
    private List<Varient> varientList =new ArrayList<>();
}