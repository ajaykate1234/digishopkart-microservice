package com.digishopkart.customer_service.repository;

import com.digishopkart.customer_service.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress,Long> {
}
