package com.digishopkart.discount_service.controller;

import com.digishopkart.discount_service.entity.DiscountCoupon;
import com.digishopkart.discount_service.enums.CouponType;
import com.digishopkart.discount_service.services.DiscountCouponService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DiscountCouponControllerTest {

    @Mock
    private DiscountCouponService discountCouponService;

    @InjectMocks
    private DiscountCouponController discountCouponController;

    private DiscountCoupon testCoupon;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testCoupon = DiscountCoupon.builder()
                .id(1L)
                .couponType(CouponType.FLAT)
                .value(50.0)
                .build();
    }

    @Test
    void testAddDiscountCoupon() {
        when(discountCouponService.addDiscountCouponService(any())).thenReturn(testCoupon);
        ResponseEntity<DiscountCoupon> response = discountCouponController.addDiscountCoupon(testCoupon);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testCoupon, response.getBody());
    }

    @Test
    void testAddDiscountCoupon_Failure() {
        when(discountCouponService.addDiscountCouponService(any())).thenReturn(null);
        ResponseEntity<DiscountCoupon> response = discountCouponController.addDiscountCoupon(testCoupon);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testUpdateDiscountCoupon() {
        when(discountCouponService.updateDiscountCouponService(eq(1L), any())).thenReturn(testCoupon);
        ResponseEntity<DiscountCoupon> response = discountCouponController.updateDiscountCoupon(1L, testCoupon);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testCoupon, response.getBody());
    }

    @Test
    void testUpdateDiscountCoupon_Failure() {
        when(discountCouponService.updateDiscountCouponService(eq(1L), any())).thenReturn(null);
        ResponseEntity<DiscountCoupon> response = discountCouponController.updateDiscountCoupon(1L, testCoupon);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testFetchDiscountCoupon() throws Exception {
        when(discountCouponService.fetchDiscountCouponByIdService(1L)).thenReturn(testCoupon);
        ResponseEntity<DiscountCoupon> response = discountCouponController.fetchDiscountCoupon(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testCoupon, response.getBody());
    }

    @Test
    void testFetchDiscountCoupon_Failure() throws Exception {
        when(discountCouponService.fetchDiscountCouponByIdService(1L)).thenThrow(new Exception("Not found"));
        Exception exception = assertThrows(Exception.class, () -> {
            discountCouponController.fetchDiscountCoupon(1L);
        });
        assertEquals("Not found", exception.getMessage());
    }

    @Test
    void testDeleteDiscountCoupon() {
        when(discountCouponService.deleteDiscountCouponByIdService(1L)).thenReturn("Deleted");
        ResponseEntity<String> response = discountCouponController.deleteDiscountCoupon(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted", response.getBody());
    }

    @Test
    void testDeleteDiscountCoupon_Failure() {
        when(discountCouponService.deleteDiscountCouponByIdService(1L)).thenReturn("Not found");
        ResponseEntity<String> response = discountCouponController.deleteDiscountCoupon(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Not found", response.getBody());
    }

    @Test
    void testFetchAllDiscountCoupons() {
        List<DiscountCoupon> coupons = Arrays.asList(testCoupon);
        Page<DiscountCoupon> page = new PageImpl<>(coupons);
        Pageable pageable = PageRequest.of(0, 5);
        when(discountCouponService.fetchAllDiscountCouponsService(pageable)).thenReturn(page);
        ResponseEntity<Page> response = discountCouponController.fetchAllDiscountCoupons(5, 0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getTotalElements());
    }

    @Test
    void testFetchAllDiscountCoupons_Failure() {
        Pageable pageable = PageRequest.of(0, 5);
        when(discountCouponService.fetchAllDiscountCouponsService(pageable)).thenReturn(Page.empty());
        ResponseEntity<Page> response = discountCouponController.fetchAllDiscountCoupons(5, 0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().getTotalElements());
    }

}
