package com.digishopkart.discount_service.entity;

import com.digishopkart.discount_service.enums.CouponType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DiscountCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CouponType couponType;
    private Double value;

}
