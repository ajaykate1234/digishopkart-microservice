package com.digishopkart.product_service.services;

import com.digishopkart.product_service.entity.Kart;
import com.digishopkart.product_service.repository.KartRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Log4j2
public class KartService {

    @Autowired
    private KartRepository kartRepository;
    public Kart addItemsToKartServcie(Kart kart){
        try {
            Optional<Kart> optionalKart = kartRepository.findByCustomerId(kart.getCustomerId());
            if (optionalKart.isPresent()){
                Kart k = optionalKart.get();
                k.setProductList(kart.getProductList());
                log.info("addItemsToKartServcie : k:{}",k);
                Kart response = kartRepository.save(k);
                log.info("addItemsToKartServcie : response :{}",response);
                return response;
            }else {
                log.info("addItemsToKartServcie : k:{}",kart);
                Kart response = kartRepository.save(kart);
                log.info("addItemsToKartServcie : response :{}",response);
                return response;
            }

        }catch (Exception e){
            log.error("addItemsToKartServcie : Exception :{}",e);
            throw new RuntimeException("Error While adding Item to Kart");
        }
    }

    public Kart fetchKartItemsForCustomer(Long customerId){
        try {
            Optional<Kart> optionalKart = kartRepository.findByCustomerId(customerId);
            if (optionalKart.isPresent()){
                log.info("fetchKartItemsForCustomer : response :{}",optionalKart.get());
                return optionalKart.get();
            }else {
                log.info("fetchKartItemsForCustomer : Kart not present for this customerId");
                return null;
            }

        }catch (Exception e){
            log.error("fetchKartItemsForCustomer Exception :{}",e);
            throw new RuntimeException("Error while fetching Items from Kart");
        }
    }
}
