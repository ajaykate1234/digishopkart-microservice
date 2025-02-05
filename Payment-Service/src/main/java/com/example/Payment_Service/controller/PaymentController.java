package com.example.Payment_Service.controller;

import com.example.Payment_Service.entity.Payment;
import com.example.Payment_Service.services.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("digi")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

//    @GetMapping(path = "/payment")
//    public ResponseEntity<Payment> makepayment(@RequestBody Payment payment) throws RazorpayException {
//        paymentService.createPayment(payment);
//        return new ResponseEntity<>(null, HttpStatus.CREATED);
//    }

    // -- API working fine
    @PostMapping("/create-order")
    public String createOrder(@RequestParam double amount) {
        try {
            return paymentService.createOrder(amount);
        } catch (RazorpayException e) {
            return "Error: " + e.getMessage();
        }
    }

    // need to add webhook url in razarpay portal , So getting - Payment verification failed
    @PostMapping("/verify-payment")
    public String verifyPayment(@RequestParam String orderId,
                                @RequestParam String paymentId,
                                @RequestParam String signature) {
        boolean isValid = paymentService.verifyPayment(orderId, paymentId, signature);
        return isValid ? "Payment verified successfully" : "Payment verification failed";
    }
}
