package com.digishopkart.product_service.util;

import com.digishopkart.product_service.entity.Product;
import com.digishopkart.product_service.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import com.example.productapp.repository.ProductRepository;
import java.time.LocalDate;

@Component
@Log4j2
public class ProductExpiryJob implements Job {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void execute(JobExecutionContext context) {
        // Fetch products with today's expiry date
        LocalDate today = LocalDate.now();
        try {

            var expiredProducts = productRepository.findByExpiryDate(today);

            if (!expiredProducts.isEmpty()) {
                expiredProducts.forEach(product -> {
                    //                System.out.println("Notification: Product '" + product.getProductName() + "' has expired!");
                    if (!product.getIsExpired()) {
                        Product p = markProductExpied(product);
                        log.info("execute. # Today Expired Product :[ Product Name : {}, Expiry Date:{}, IsExpired Flag:{}]", p.getProductName(), p.getExpiryDate(), p.getIsExpired());
                    } else {
                        log.info("execute. # Previously Expired Product :[Product Name : {}, Expiry Date:{}, IsExpired Flag:{}],", product.getProductName(), product.getExpiryDate(), product.getIsExpired());
                    }
                });
            }
        }catch (Exception e){
            log.error("execute : Exception ",e);
        }
    }

    // Making flag true of today expired product
    public Product markProductExpied(Product product){
        product.setIsExpired(true);
        return productRepository.save(product);

    }
}
