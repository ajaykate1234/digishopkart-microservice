package com.digishopkart.product_service.repository;

import com.digishopkart.product_service.entity.Kart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KartRepository extends JpaRepository<Kart,Long> {

    Optional<Kart> findByCustomerId(Long customerId);

}
