package com.digishopkart.customer_service.service;

import com.digishopkart.customer_service.entity.Customer;
import com.digishopkart.customer_service.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomerService(Customer customer) {
        try {
           Customer response= customerRepository.save(customer);
           log.info("addCustomerService : response : {}",response);
            return customer;

        }catch (Exception e){
            log.error("addCustomerService : Exception:{}",e);
            return null;
        }
    }
}
