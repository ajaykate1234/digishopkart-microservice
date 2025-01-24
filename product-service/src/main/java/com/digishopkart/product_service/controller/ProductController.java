package com.digishopkart.product_service.controller;


import com.digishopkart.product_service.entity.Product;
import com.digishopkart.product_service.entity.Varient;
import com.digishopkart.product_service.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/digi")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value= "/product/add",consumes = "multipart/form-data")
    public ResponseEntity<Product> addProduct(@RequestPart("product") String  productJson, // @RequestBody cannot work with multipart/form-data.
                                              @RequestParam(value = "file") MultipartFile file) throws Exception {

        // Convert the JSON string to a Product object
       // ObjectMapper objectMapper = new ObjectMapper();

//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule()); // Register JavaTimeModule
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Optional: To use ISO-8601 format
//
//        Product product = objectMapper.readValue(productJson, Product.class);

        // Create and configure ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Register JavaTimeModule
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Optional: To use ISO-8601 format

        // Deserialize JSON to Product object
        Product product = objectMapper.readValue(productJson, Product.class);


        Product res =  productService.addProductService(product,file);
       return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/product/update")
    public ResponseEntity<Product> updateProduct(@RequestParam("id") Long id,
                                              @RequestBody Product product) throws Exception {

        Product res =  productService.updateProductService(id,product);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/product/fetch")
    public ResponseEntity<Product> fetchProduct(@RequestParam("id") Long id) throws Exception {

        Product res =  productService.fetchProductByIdService(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/product/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam("id") Long id) throws Exception {

        String res =  productService.deleteProductByIdService(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/product/fetchAll")
    public ResponseEntity<Page> fetchAllProducts(@RequestParam("pageSize") Integer pageSize,
                                                 @RequestParam("pageNo") Integer pageNo){
        Pageable pageable= PageRequest.of(pageNo,pageSize);

        Page res = productService.fetchAllProductsService(pageable);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

// API to fetch Varients of product by Id
    @GetMapping("/product/varients/fetch")
    public ResponseEntity<List<Varient>> getPrductVarients(@RequestParam("productId") Long productId){

        List<Varient>  varients=  productService.fetchAllProductVarientsByProductIdService(productId);
            return new ResponseEntity<>( varients, HttpStatus.OK);
    }

// Add Varient for previous product
    @PostMapping("/product/varients/add")
    public ResponseEntity<Product> addVarientsForProduct(@RequestParam("productId") Long productId ,
                                                         @RequestBody List<Varient> varientList){
        Product res = productService.addVarientsForPreviousProductService(productId, varientList);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

//    @Scheduled(cron = "0/2 * * * * ?")
//    public void testScheduling(){
//        System.out.println("Current time: "+ LocalDateTime.now());
//
//    }
}
