package com.example.Payment_Service.services;

import com.example.Payment_Service.entity.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.annotation.PostConstruct;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
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
}
