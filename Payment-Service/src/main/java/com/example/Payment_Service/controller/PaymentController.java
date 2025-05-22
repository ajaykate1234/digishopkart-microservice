package com.example.Payment_Service.controller;

import com.example.Payment_Service.services.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("digi")
@CrossOrigin(origins = "*")
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
    @PostMapping("/verify-payment-old")
    public String verifyPayment(@RequestParam String orderId,
                                @RequestParam String paymentId,
                                @RequestParam String signature) {
        boolean isValid = paymentService.verifyPayment(orderId, paymentId, signature);
        return isValid ? "Payment verified successfully" : "Payment verification failed";
    }

    // new endpoint
    @PostMapping("/verify-payment")
    public String verifyPayment1(@RequestBody Map<String, String> data) {
        String paymentId = data.get("razorpay_payment_id");
        String orderId = data.get("razorpay_order_id");
        String signature = data.get("razorpay_signature");

        boolean isValid = paymentService.verifyPayment(orderId, paymentId, signature);
        return isValid ? "Payment verified successfully" : "Payment verification failed";
    }
}
