package com.digishopkart.customer_service.service;

import com.digishopkart.customer_service.entity.Customer;
import com.digishopkart.customer_service.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
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

    public Customer fetchCustomerByIdService(Long id) {
        try {
            Optional<Customer> optionalCustomer = customerRepository.findById(id);
            if (optionalCustomer.isPresent()){
                log.info("fetchCustomerById: res:{}",optionalCustomer.get());
                return optionalCustomer.get();
            }
            return null;
        }catch (Exception e){
            log.error("Exception :{}",e);
            return null;
        }
    }

    public String deleteCustomerByIdService(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()){
            customerRepository.deleteById(id);
            return "customer Deleted Successfully";
        }
        return "customer Not Presnet for id: "+id;
    }

    public Customer updateCustomerByIdService(Long id, Customer customer) {
        try {
            Optional<Customer> optionalCustomer = customerRepository.findById(id);
            if (optionalCustomer.isPresent()){
                customerRepository.save(customer);
                log.info("customer Updated Successfully");
            }
            log.info("customer Not Presnet for id: "+id);
            return null;
        }catch (Exception e){
            log.error("Exception:{}",e);
            return null;
        }
    }

    public org.springframework.data.domain.Page<Customer> fetchAllCustomersService(Pageable pageable) {
        try {
        Page<Customer> res = customerRepository.findAll(pageable);
            log.info("Res : {}",res);
        return res;
        }catch (Exception e){
            log.error("Exception:{}",e);
            return null;
        }
    }
}
