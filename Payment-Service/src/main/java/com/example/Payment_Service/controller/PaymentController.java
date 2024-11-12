package com.example.Payment_Service.controller;

import com.example.Payment_Service.entity.Payment;
import com.example.Payment_Service.services.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("digi")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(path = "/payment")
    public ResponseEntity<Payment> makepayment(@RequestBody Payment payment) throws RazorpayException {
        paymentService.createPayment(payment);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
