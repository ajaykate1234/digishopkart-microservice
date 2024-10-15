package com.digishopkart.discount_service.repository;

import com.digishopkart.discount_service.entity.DiscountCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCouponRepository extends JpaRepository<DiscountCoupon,Long> {

}
