package com.digishopkart.order_service.services;

import com.digishopkart.order_service.enums.OrderStatus;
import com.digishopkart.order_service.dto.*;
import com.digishopkart.order_service.entity.Order;
import com.digishopkart.order_service.enums.AddressType;
import com.digishopkart.order_service.enums.CouponType;
import com.digishopkart.order_service.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
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

@Log4j2
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

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private DiscountServiceClient discountServiceClient;

    @Autowired
    private CustomerServiceClient customerServiceClient;

    public Order placeOrderService(Long customerId, Long productId, Long discountCouponId, Long varientId) {
        try {
            log.info("placeOrderService : customerId{}, productId{}, discountCouponId{}, varientId{}",
                    customerId,productId,discountCouponId,varientId);
            RestTemplate restTemplate = new RestTemplate();

            log.info("customerUrl :{} " ,customerServiceUrl);
//            ResponseEntity<Customer> customerResponse = restTemplate.getForEntity(customerServiceUrl+"/customer/fetch?id="+customerId, Customer.class);
//            ResponseEntity<Product> productResponse = restTemplate.getForEntity(productServiceUrl+"/product/fetch?id="+productId, Product.class);
//            ResponseEntity<DiscountCoupon> discountCouponResponse = restTemplate.getForEntity(discountServiceUrl+"/discount/fetchCoupon?id="+discountCouponId, DiscountCoupon.class);


            // Using FeignClient
            Customer customerResponse = customerServiceClient.getCustomerById(customerId);
            log.info("placeOrderService: customerResponse : {}",customerResponse);

            Product productResponse = productServiceClient.getProductById(productId);
            log.info("placeOrderService: productResponse : {}",productResponse);

            DiscountCoupon discountCouponResponse = discountServiceClient.getDiscountCouponByid(discountCouponId);
            log.info("placeOrderService: discountCouponResponse: {} : ",discountCouponResponse);

            Order order = createOrder(customerResponse,productResponse,discountCouponResponse,varientId);
            Order res = orderRepository.save(order);
            log.info("placeOrderService : Plcaed order :{}",res);
            return res;

        } catch (Exception e) {
            log.error("Exceptionnn:{}",e);
            return null;
        }
    }

    // Create order Service
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

                Double finalPrice= calculateDiscount(discountCoupon,product);

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

    // Method to calculate Discount Price
    public Double calculateDiscount(DiscountCoupon discountCoupon, Product product){
        Double finalPrice = 0.0;
        if (discountCoupon.getCouponType().equals(CouponType.FLAT)){
            finalPrice = product.getProductPrice()-discountCoupon.getValue();
            log.info("createOrder :FLAT final price : {}",finalPrice);
            return finalPrice;
        } else if (discountCoupon.getCouponType().equals(CouponType.PERCENTAGE)) {
            finalPrice = product.getProductPrice() - (product.getProductPrice() * discountCoupon.getValue() )/ 100;
            log.info("createOrder :PERCENTAGE final price : {}",finalPrice);
            return finalPrice;
        }
        return finalPrice;
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
