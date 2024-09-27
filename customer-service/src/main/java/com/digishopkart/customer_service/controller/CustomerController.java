package com.digishopkart.customer_service.controller;

import com.digishopkart.customer_service.entity.Customer;
import com.digishopkart.customer_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/digi")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
       Customer response = customerService.addCustomerService(customer);
       return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(){
        return "tested";
    }
}
