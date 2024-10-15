package com.digishopkart.order_service.controller;

import com.digishopkart.order_service.entity.Order;
import com.digishopkart.order_service.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        if(response==null){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/order/getAll")
    public ResponseEntity<Page<Order>> getAllOrders(@RequestParam("pageSize") Integer pageSize,
                                                    @RequestParam("pageNo") Integer pageNo){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Order> orders = orderService.getAllOrdersByPagging(pageable);

        if(orders==null){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
