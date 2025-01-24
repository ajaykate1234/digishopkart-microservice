package com.digishopkart.customer_service.controller;

import com.digishopkart.customer_service.entity.Customer;
import com.digishopkart.customer_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/digi")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
       Customer response = customerService.addCustomerService(customer);
       return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/customer/fetch")
    public ResponseEntity<Customer> fetchCustomer(@RequestParam("id") Long id){
        Customer response = customerService.fetchCustomerByIdService(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/customer/delete")
    public ResponseEntity<String> deleteCustomer(@RequestParam("id") Long id){
        String res = customerService.deleteCustomerByIdService(id);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PutMapping("/customer/update")
    public ResponseEntity<Customer> updateCustomer(@RequestParam("id") Long id, @RequestBody Customer customer){
        Customer res = customerService.updateCustomerByIdService(id,customer);
        return new ResponseEntity<>(res,HttpStatus.OK);

    }


    @GetMapping("/customer/fetchAll")
    public ResponseEntity<Page<Customer>> fetchAllCustomers(@RequestParam("pageNo") Integer pageNo,
                                                            @RequestParam("pageSize") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize); // page no starts from 0

        Page<Customer> response = customerService.fetchAllCustomersService(pageable);
        return new ResponseEntity<Page<Customer>>(response, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(){
        return "tested";
    }
}
