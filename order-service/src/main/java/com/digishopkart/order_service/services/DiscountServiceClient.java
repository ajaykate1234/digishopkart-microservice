package com.digishopkart.order_service.services;

import com.digishopkart.order_service.dto.DiscountCoupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "discount-service", url = "http://localhost:8083/digi")
public interface DiscountServiceClient {

    @GetMapping("/discount/fetchCoupon")
    DiscountCoupon getDiscountCouponByid(@RequestParam("id") Long id);
}
