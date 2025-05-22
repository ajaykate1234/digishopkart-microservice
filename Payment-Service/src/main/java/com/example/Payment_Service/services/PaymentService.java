package com.example.Payment_Service.services;

import com.example.Payment_Service.entity.Payment;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Service
@Log4j2
public class PaymentService {

    @Value("${kyc.razor.key}")
    private String razorpayId;

    @Value("${kyc.razor.secret}")
    private String razorpaySecret;

    private RazorpayClient razorpayClient;

    @PostConstruct
    public void init() throws RazorpayException {
        this.razorpayClient = new RazorpayClient(razorpayId,razorpaySecret);
    }

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;


    public String createOrder(double amount) throws RazorpayException {
        RazorpayClient client = new RazorpayClient(keyId, keySecret);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", (int)(amount * 100)); // Amount in paise (INR)
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", UUID.randomUUID().toString());
        orderRequest.put("payment_capture", 1); // Auto capture payment

        Order order = client.orders.create(orderRequest);
        return order.toString();
    }

    public com.razorpay.Payment createPayment(Payment payment) throws RazorpayException {
        JSONObject json = new JSONObject();
        json.put("amount",payment.getAmount());
        json.put("currency","INR");
        json.put("receipt",payment.getReceipt());
        json.put("url","https://api.razorpay.com/v1/payments");
        com.razorpay.Payment razorPayment = razorpayClient.payments.createJsonPayment(json);

        System.out.println("razorPayment : "+razorPayment);
        return razorPayment;

    }


    public boolean verifyPayment(String orderId, String paymentId, String signature) {
        try {
            String data = orderId + "|" + paymentId;
            String generatedSignature = hmacSHA256(data, keySecret);

            log.info("Generated Signature: {}",generatedSignature);

            return generatedSignature.equals(signature);
        } catch (Exception e) {
            return false;
        }
    }

//    private String hmacSHA256(String data, String key) throws Exception {
//        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
//        javax.crypto.spec.SecretKeySpec secretKeySpec = new javax.crypto.spec.SecretKeySpec(key.getBytes(), "HmacSHA256");
//        mac.init(secretKeySpec);
//        byte[] hash = mac.doFinal(data.getBytes());
//        return new java.util.Base64.Encoder().encodeToString(hash);
//    }

    public String hmacSHA256(String data, String key) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        mac.init(secretKeySpec);
        byte[] hash = mac.doFinal(data.getBytes());

        // Correct way to encode in Base64
        return Base64.getEncoder().encodeToString(hash);
    }

}
