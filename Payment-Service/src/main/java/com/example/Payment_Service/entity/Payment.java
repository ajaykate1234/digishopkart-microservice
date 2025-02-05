package com.example.Payment_Service.entity;

import com.example.Payment_Service.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

//@Entity
@Data
public class Payment {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;
    private double amount;
    private String currency;
    private String receipt;
    private String url;

    private PaymentStatus status;



}
