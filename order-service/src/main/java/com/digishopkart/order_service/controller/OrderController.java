package com.digishopkart.order_service.controller;

import com.digishopkart.order_service.entity.Order;
import com.digishopkart.order_service.services.OrderService;
import com.digishopkart.order_service.services.ProductServiceClient;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Log4j2
@RestController
@RequestMapping("digi")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestParam("customerId") Long customerId,
                                            @RequestParam("productId") Long productId,
                                            @RequestParam("discountCouponId") Long discountCouponId,
                                            @RequestParam("varientId") Long varientId){
        log.info("Srart of placeOrder API , customerId{}",customerId);
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

    @Autowired
    private ProductServiceClient productServiceClient;

    @GetMapping("/test")
    public ResponseEntity<String> testAPI(){
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJCaGFsdSIsImlhdCI6MTczNjQxNTQ0MywiZXhwIjoxNzM2NDMzNDQzfQ.RadGsUDSzZ7kGDs5Z9gS0BNFMSXvOqDwM8x_h8uBikGfO_jHo0N9FfK21EbuJWjNG3t4kxhyrDiAZ7dTbHU9sg";
        String url = "http://localhost:8082/digi/product/fetch?id=253";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+token);

        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<String> productResponse = restTemplate.exchange(url, HttpMethod.GET,httpEntity,String.class);

//        Product productResponse = productServiceClient.getProductById(1);
        log.info("placeOrderService: productResponse : {}",productResponse);
        return productResponse;
    }

    @GetMapping("/name")
    public String getname(){
        return "Ajay";
    }

}
