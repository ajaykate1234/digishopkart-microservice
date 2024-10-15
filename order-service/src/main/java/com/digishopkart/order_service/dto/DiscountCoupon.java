package com.digishopkart.order_service.dto;

import com.digishopkart.order_service.enums.CouponType;
import lombok.Data;

@Data
public class DiscountCoupon {

    private Long id;
    private CouponType couponType;
    private Double value;

}
