package com.digishopkart.order_service.entity;

import com.digishopkart.order_service.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orderTable")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Boolean active = true;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String fullName;
    private String address;
    private Double finalPrice;

    private String productName;
    private String varientDetails;

}
