package com.digishopkart.subscription_service.controller;

import com.digishopkart.subscription_service.entity.Subscription;
import com.digishopkart.subscription_service.services.SubscriptionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/digi/subscription")
public class SubscriptionController {


    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/create")
    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription){

       Subscription s  =  subscriptionService.createSubscriptionService(subscription);
       System.out.println("Response :"+s);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}
