package com.digishopkart.product_service.repository;

import com.digishopkart.product_service.entity.Varient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VarientRepository extends JpaRepository<Varient,Long> {

}
