package com.digishopkart.customer_service.repository;

import com.digishopkart.customer_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);

}
