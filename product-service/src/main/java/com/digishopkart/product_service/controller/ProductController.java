package com.digishopkart.product_service.controller;


import com.digishopkart.product_service.entity.Product;
import com.digishopkart.product_service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/digi")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) throws Exception {

       Product res =  productService.addProductService(product);
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
}
