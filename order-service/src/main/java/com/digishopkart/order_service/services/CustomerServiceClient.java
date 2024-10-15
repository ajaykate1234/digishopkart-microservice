package com.digishopkart.order_service.services;

import com.digishopkart.order_service.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "customer-service", url = "http://localhost:8081/digi")
@FeignClient(name = "customer-service", value = "customer-service")
public interface CustomerServiceClient {

    @GetMapping("/digi/customer/fetch")
    Customer getCustomerById(@RequestParam("id") Long id);
}
