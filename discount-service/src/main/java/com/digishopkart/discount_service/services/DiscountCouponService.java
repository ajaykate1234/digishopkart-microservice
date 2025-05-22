package com.digishopkart.discount_service.services;

import com.digishopkart.discount_service.entity.DiscountCoupon;
import com.digishopkart.discount_service.repository.DiscountCouponRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class DiscountCouponService {

    @Autowired
    private DiscountCouponRepository discountCouponRepository;

    public DiscountCoupon addDiscountCouponService(DiscountCoupon discountCoupon) {
        try {
            DiscountCoupon res = discountCouponRepository.save(discountCoupon);
            log.info("addDiscountCouponService : res:{}",res);
            return res;
        }catch (Exception e){
            log.error("addDiscountCouponService : Exception:{}",e);
            return null;
        }
    }

    @Transactional()
    public DiscountCoupon updateDiscountCouponService(Long id, DiscountCoupon discountCoupon) {
        try {
            Optional<DiscountCoupon> optionalDiscountCoupon = discountCouponRepository.findById(id);
            if (optionalDiscountCoupon.isPresent()){
                discountCoupon.setId(optionalDiscountCoupon.get().getId());
                DiscountCoupon res = discountCouponRepository.save(discountCoupon);
                log.info("updateDiscountCouponService : res : {}",res);
                return res;
            }
            log.info("updateDiscountCouponService: DiscountCoupon not found for id :{}",id);
            return null;
        }catch (Exception e){
            log.error("updateDiscountCouponService : Exception:{}",e);
            return null;
        }
    }

    public DiscountCoupon fetchDiscountCouponByIdService(Long id) throws Exception {
        try {
            Optional<DiscountCoupon> optionalDiscountCoupon = discountCouponRepository.findById(id);
            if (optionalDiscountCoupon.isPresent()){
                log.info("fetchDiscountCouponByIdService: res : {}",optionalDiscountCoupon.get());
                return optionalDiscountCoupon.get();
            }
            log.info("fetchDiscountCouponByIdService: DiscountCoupon not found for id :{}",id);
            throw new Exception("DiscountCoupon not found for id : "+id);
        }catch (Exception e){
            log.error("fetchDiscountCouponByIdService: Exception :{}",e);
            throw e;
        }
    }

    public String deleteDiscountCouponByIdService(Long id) {
        try {
            Optional<DiscountCoupon> optionalDiscountCoupon = discountCouponRepository.findById(id);
            if (optionalDiscountCoupon.isPresent()){
                discountCouponRepository.deleteById(id);
                return "deleteDiscountCouponByIdService: deleted DiscountCoupon for id: "+id;
            }
            return "deleteDiscountCouponByIdService: DiscountCoupon not found for id :"+id;
        }catch (Exception e){
            log.error("deleteDiscountCouponByIdService: Exception :{}",e);
            return "Error While deleting coupon : "+e.getMessage();
        }
    }

    public Page fetchAllDiscountCouponsService(Pageable pageable) {
        try {
            Page res = discountCouponRepository.findAll(pageable);
            log.info("fetchAllDiscountCouponsService: {}",res);
            return res;
        }catch (Exception e){
            log.error("fetchAllDiscountCouponsService: exception :{}",e);
            return null;
        }
    }
}
