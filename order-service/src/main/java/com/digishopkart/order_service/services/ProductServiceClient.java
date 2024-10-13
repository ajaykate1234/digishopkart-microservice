package com.digishopkart.order_service.services;

import com.digishopkart.order_service.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service", url = "http://localhost:8082/digi")
public interface ProductServiceClient {

    @GetMapping("/product/fetch")
    Product getProductById(@RequestParam("id") long id);
}
