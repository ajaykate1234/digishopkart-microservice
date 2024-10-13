package com.digishopkart.order_service.services;

import com.digishopkart.order_service.enums.OrderStatus;
import com.digishopkart.order_service.dto.*;
import com.digishopkart.order_service.entity.Order;
import com.digishopkart.order_service.enums.AddressType;
import com.digishopkart.order_service.enums.CouponType;
import com.digishopkart.order_service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OrderService {

    @Value("${external.service.customer.url}")
    private String customerServiceUrl;

    @Value("${external.service.product.url}")
    private String productServiceUrl;

    @Value("${external.service.discount.url}")
    private String discountServiceUrl;

    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrderService(Long customerId, Long productId, Long discountCouponId, Long varientId) {
        try {
            log.info("placeOrderService : customerId{}, productId{}, discountCouponId{}, varientId{}",
                    customerId,productId,discountCouponId,varientId);
            RestTemplate restTemplate = new RestTemplate();

            log.info("customerUrl :{} " ,customerServiceUrl);
            ResponseEntity<Customer> customerResponse = restTemplate.getForEntity(customerServiceUrl+"/customer/fetch?id="+customerId, Customer.class);
            ResponseEntity<Product> productResponse = restTemplate.getForEntity(productServiceUrl+"/product/fetch?id="+productId, Product.class);
            ResponseEntity<DiscountCoupon> discountCouponResponse = restTemplate.getForEntity(discountServiceUrl+"/discount/fetchCoupon?id="+discountCouponId, DiscountCoupon.class);

            log.info("placeOrderService: customerResponse : {}",customerResponse);
            log.info("placeOrderService: productResponse : {}",productResponse);
            log.info("placeOrderService: discountCouponResponse: {} : ",discountCouponResponse);

            Order order = createOrder(customerResponse.getBody(),productResponse.getBody(),discountCouponResponse.getBody(),varientId);
            Order res = orderRepository.save(order);
            log.info("placeOrderService : Plcaed order :{}",res);
            return res;

        } catch (Exception e) {
            log.error("Exception :{}", e);
            return null;
        }
    }

    public Order createOrder (Customer customer, Product product, DiscountCoupon discountCoupon,Long varientId){
        try {

            if (customer!=null && product!=null && discountCoupon!=null) {
                String address = "";
                for (int i = 0; i < customer.getCustomerAddressList().size() - 1; i++) {
                    if (customer.getCustomerAddressList().get(i).getAddressType().equals(AddressType.CURRENT)) {
                        CustomerAddress ca = customer.getCustomerAddressList().get(i);
                        address = ca.getHouseOrBuilding() + ", " + ca.getArea() + ", " + ca.getLandmark() + ", " + ca.getTown() + ", " +
                                ", " + ca.getState() + ", " + ca.getCountry() + ", " + ca.getPinCode();

                    }
                }

                String varientDetails = "";
                List<Varient> varientList =  product.getVarientList();
                for (int i=0; i<varientList.size()-1;i++){
                    if (varientList.get(i).getId().equals(varientId)){
                        varientDetails = varientList.get(i).getName()+", "+varientList.get(i).getValue();
                    }
                }

                Double finalPrice= null;
                if (discountCoupon.getCouponType().equals(CouponType.FLAT)){
                    finalPrice = product.getProductPrice()-discountCoupon.getValue();
                    log.info("createOrder :FLAT final price : {}",finalPrice);
                } else if (discountCoupon.getCouponType().equals(CouponType.PERCENTAGE)) {
                    finalPrice = product.getProductPrice() - (product.getProductPrice() * discountCoupon.getValue() )/ 100;
                    log.info("createOrder :PERCENTAGE final price : {}",finalPrice);
                }


                Order order = new Order();
                order.setFullName(customer.getName());
                order.setCreatedDate(LocalDateTime.now());
                order.setUpdatedDate(LocalDateTime.now());
                order.setOrderStatus(OrderStatus.PLACED);
                order.setAddress(address);
                order.setProductName(product.getProductName());
                order.setVarientDetails(varientDetails);
                order.setFinalPrice(finalPrice);
                return order;
            }
        }catch (Exception e){
            log.error("createOrder: Exception :{}",e);
            return null;
        }
        return null;
    }

    public Page<Order> getAllOrdersByPagging(Pageable pageable) {
        try {
            return orderRepository.findAll(pageable);
        }catch (Exception e){
            log.error("Exception : {}",e);
            return null;
        }
    }
}
