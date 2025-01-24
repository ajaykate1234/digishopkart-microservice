package com.digishopkart.product_service.controller;

import com.digishopkart.product_service.entity.Kart;
import com.digishopkart.product_service.services.KartService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/digi/kart")
@CrossOrigin(origins = ("*"))
@Log4j2
public class KartController {

    @Autowired
    private KartService kartService;

  //-- Endpoint to add Items to kart for customer ---
    @PostMapping("/addItem")
    public ResponseEntity<Kart> addItemsToKart(@RequestBody Kart kart){
        Kart res =  kartService.addItemsToKartServcie(kart);
        log.info("addItemsToKart : res: {}",res);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/fetchItems")
    public ResponseEntity<Kart> fetchKartItemsForCustomer(@RequestParam Long customerId){
        Kart res = kartService.fetchKartItemsForCustomer(customerId);
        log.info("fetchKartItemsForCustomer : res :{} ",res);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
