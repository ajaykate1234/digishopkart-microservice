package com.digishopkart.discount_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.digishopkart.discount_service.entity.DiscountCoupon;
import com.digishopkart.discount_service.enums.CouponType;
import com.digishopkart.discount_service.repository.DiscountCouponRepository;
import com.digishopkart.discount_service.services.DiscountCouponService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Arrays;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class DiscountCouponServiceTest {

    @Mock
    private DiscountCouponRepository couponRepository; // Mocking repository

    @InjectMocks
    private DiscountCouponService couponService;

    private DiscountCoupon discountCoupon;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this); // Manual initialization
//    }

//  ✅ No need to add this manual initialization because we added @ExtendWith annotation at class name
//  ✅ @ExtendWith(MockitoExtension.class) auto-initializes Mockito mocks.
//  ✅ Eliminates the need for MockitoAnnotations.initMocks(this);.
//  ✅ Makes tests cleaner and reduces boilerplate code.

    @BeforeEach
    void setUp() {
//      MockitoAnnotations.initMocks(this);
        discountCoupon = new DiscountCoupon();
        discountCoupon.setId(1l);
        discountCoupon.setCouponType(CouponType.FLAT);
        discountCoupon.setValue(100.0);
    }

// ✅ Test: Add Discount Coupon
    @Test
    void testAddDiscountCouponService(){
        when(couponRepository.save(discountCoupon)).thenReturn(discountCoupon);

        DiscountCoupon result =couponService.addDiscountCouponService(discountCoupon);
        assertNotNull(result);
        assertEquals(CouponType.FLAT,result.getCouponType());

        // Verify that `save()` was called exactly **once**
        verify(couponRepository, times(1)).save(discountCoupon);
    }

// ✅ Test: Update Discount Coupon (Success)
    @Test
    void testUpdateDiscountCouponService(){
        when(couponRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(discountCoupon));
        when(couponRepository.save(discountCoupon)).thenReturn(discountCoupon);

        DiscountCoupon result = couponService.updateDiscountCouponService(1l,discountCoupon);

        assertEquals(CouponType.FLAT,result.getCouponType());
        assertNotNull(result);
        verify(couponRepository,times(1)).save(discountCoupon);
    }

    // ✅ Test: Update Discount Coupon (Not Found)
    @Test
    void testUpdateDiscountCouponService_NotFound(){
        when(couponRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        DiscountCoupon result = couponService.updateDiscountCouponService(1l,discountCoupon);

        assertNull(result);
        verify(couponRepository, never()).save(discountCoupon);

    }

    // ✅ Test: Fetch Discount Coupon By ID (Success)
    @Test
    void testFetchDiscountCouponByIdService() throws Exception {
        when(couponRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(DiscountCoupon.builder().id(1l).couponType(CouponType.FLAT).value(100.0).build())); // Mocking method

        DiscountCoupon result = couponService.fetchDiscountCouponByIdService(1l);

        assertEquals(CouponType.FLAT, result.getCouponType());
        assertEquals(100.0,result.getValue());
    }

    // ✅ Test: Fetch Discount Coupon By ID (Not Found)
//    @Test
//    void testFetchDiscountCouponByIdService_NotFound() throws Exception {
//        when(couponRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
//
//        DiscountCoupon result  = couponService.fetchDiscountCouponByIdService(1l);
//        Exception e = assertThrows(Exception.class ,()->couponService.fetchDiscountCouponByIdService(1l));
//        assertEquals("fetchDiscountCouponByIdService: Exception :",e);
//    }

    // ✅ Test: Delete Discount Coupon (Success)
    @Test
    void testDeleteDiscountCouponByIdService_Success() {
        when(couponRepository.findById(1L)).thenReturn(Optional.of(discountCoupon));
        doNothing().when(couponRepository).deleteById(1L);

        String result = couponService.deleteDiscountCouponByIdService(1L);

        assertEquals("deleteDiscountCouponByIdService: deleted DiscountCoupon for id: 1", result);
        verify(couponRepository, times(1)).deleteById(1L);
    }

    // ✅ Test: Delete Discount Coupon (Not Found)
    @Test
    void testDeleteDiscountCouponByIdService_NotFound() {
        when(couponRepository.findById(2L)).thenReturn(Optional.empty());

        String result = couponService.deleteDiscountCouponByIdService(2L);

        assertEquals("deleteDiscountCouponByIdService: DiscountCoupon not found for id :2", result);
        verify(couponRepository, never()).deleteById(2L);
    }

    // ✅ Test: Fetch All Discount Coupons
    @Test
    void testFetchAllDiscountCouponsService() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<DiscountCoupon> mockPage = new PageImpl<>(Arrays.asList(discountCoupon));

        when(couponRepository.findAll(pageable)).thenReturn(mockPage);

        Page result = couponService.fetchAllDiscountCouponsService(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(couponRepository, times(1)).findAll(pageable);
    }

}
