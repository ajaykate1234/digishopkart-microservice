package com.digishopkart.product_service.services;

import com.digishopkart.product_service.entity.Product;
import com.digishopkart.product_service.exception.ResourceNotFoundException;
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

    public Product addProductService(Product product) throws Exception {
        try {

            Product res = productRepository.save(product);
            log.info("addProductService: res:{}", res);
            return res;
        } catch (Exception e) {
            log.error("addProductService: Exception :{}", e);
            throw new Exception(e.getMessage());
        }
    }

    public Product fetchProductByIdService(Long id) throws Exception {
        try {

            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()) {
                log.info("fetchProductByIdService: Product :{}", optionalProduct.get());
               // int i=9/0;
                return optionalProduct.get();

            } else {
                log.info("fetchProductByIdService: Product not found in db");
                throw new ResourceNotFoundException("Product Not Found in database...!!!");
            }
        }catch(ResourceNotFoundException e){
           log.info("fetchProductByIdService: exception:{}",e);
           throw e;
        }
        catch (Exception e){
            log.error("fetchProductByIdService : Exception :{}",e);
            throw new Exception(e.getMessage());
        }
    }

    public String deleteProductByIdService(Long id) throws Exception {
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()) {
                productRepository.delete(optionalProduct.get());
                return "Product deleted successfully";
            }else {
                throw new ResourceNotFoundException("Product Not Found in database...!!!");
            }
            //return "product not found for id: "+id;
        }catch(ResourceNotFoundException e){
            log.info("deleteProductByIdService : exception:{}",e);
            throw e;
        }
        catch (Exception e){
            log.error("deleteProductByIdService : Exception :{}",e);
            throw new Exception(e.getMessage());
        }
    }

    public Product updateProductService(Long id, Product product) throws Exception {
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()){
                product.setId(optionalProduct.get().getId());
                Product res = productRepository.save(product);
                log.info("updateProductService: res:{}",res);
                return res;
            }else {
                log.info("product not found for id:{}",id);
                throw new ResourceNotFoundException("Product Not Found in database...!!!");
            }

        }catch(ResourceNotFoundException e){
            log.info("updateProductService: exception:{}",e);
            throw e;
        }
        catch (Exception e){
            log.error("updateProductService : Exception :{}",e);
            throw new Exception(e.getMessage());
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
