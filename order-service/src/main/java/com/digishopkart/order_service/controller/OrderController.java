package com.digishopkart.order_service.controller;

import com.digishopkart.order_service.entity.Order;
import com.digishopkart.order_service.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("digi")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestParam("customerId") Long customerId,
                                            @RequestParam("productId") Long productId,
                                            @RequestParam("discountCouponId") Long discountCouponId,
                                            @RequestParam("varientId") Long varientId){

        Order response = orderService.placeOrderService(customerId,productId,discountCouponId,varientId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
