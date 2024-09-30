package com.digishopkart.discount_service.controller;

import com.digishopkart.discount_service.entity.DiscountCoupon;
import com.digishopkart.discount_service.services.DiscountCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("digi")
public class DiscountCouponController {

    @Autowired
    private DiscountCouponService discountCouponService;

    @PostMapping("/discount/addCoupon")
    public ResponseEntity<DiscountCoupon> addDiscountCoupon(@RequestBody DiscountCoupon discountCoupon){
        DiscountCoupon response = discountCouponService.addDiscountCouponService(discountCoupon);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/discount/updateCoupon")
    public ResponseEntity<DiscountCoupon> updateDiscountCoupon(@RequestParam("id") Long id,
                                                               @RequestBody DiscountCoupon discountCoupon){
        DiscountCoupon response = discountCouponService.updateDiscountCouponService(id,discountCoupon);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/discount/fetchCoupon")
    public ResponseEntity<DiscountCoupon> fetchDiscountCoupon(@RequestParam("id") Long id) throws Exception {
        DiscountCoupon response = discountCouponService.fetchDiscountCouponByIdService(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("discount/deleteCoupon")
    public ResponseEntity<String> deleteDiscountCoupon(@RequestParam("id") Long id){
        String response = discountCouponService.deleteDiscountCouponByIdService(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("discount/fetchAllCoupons")
    public ResponseEntity<Page> fetchAllDiscountCoupons(@RequestParam("pageSize") Integer pageSize,
                                                        @RequestParam("pageNo") Integer pageNo){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page response = discountCouponService.fetchAllDiscountCouponsService(pageable);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
