package com.digishopkart.product_service.services;

import com.digishopkart.product_service.entity.Product;
import com.digishopkart.product_service.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepository productRepository;

    public Product addProductService(Product product) {
        try {

            Product res = productRepository.save(product);
            log.info("addProductService: res:{}", res);
            return res;
        } catch (Exception e) {
            log.error("addProductService: Exception :{}", e);
            return null;
        }
    }

    public Product fetchProductByIdService(Long id) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()) {
                log.info("fetchProductByIdService: Product :{}",optionalProduct.get());
                return optionalProduct.get();
            }
            return null;
        }catch (Exception e){
            log.error("fetchProductByIdService : Exception :{}",e);
            return null;
        }

    }

    public String deleteProductByIdService(Long id) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()) {
                productRepository.delete(optionalProduct.get());
                return "Product deleted successfully";
            }
            return "product not found for id: "+id;
        }catch (Exception e){
            log.error("deleteProductByIdService : Exception :{}",e);
            return null;
        }
    }

    public Product updateProductService(Long id, Product product) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()){
                product.setId(optionalProduct.get().getId());
                Product res = productRepository.save(product);
                log.info("updateProductService: res:{}",res);
                return res;
            }
            log.info("product not found for id:{}",id);
            return null;
        }catch (Exception e){
            log.error("updateProductService: Exception :{}",e);
            return null;
        }
    }

    public Page fetchAllProductsService(Pageable pageable) {
        try {
            Page res = productRepository.findAll(pageable);
            log.info("fetchAllProductsService : res :{}",res);
            return res;
        }catch (Exception e){
            log.error("fetchAllProductsService : Exception : {}",e);
            return null;
        }
    }
}
