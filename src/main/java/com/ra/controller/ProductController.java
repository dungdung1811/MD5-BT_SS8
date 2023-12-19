package com.ra.controller;

import com.ra.entity.Product;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/sort")
    public ResponseEntity<List<Product>> getAll(@RequestParam(required = false) String order){
        if (order!= null){
            List<Product> list = productService.sortByPrice(order);
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        List<Product> list = productService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProduct(
            @RequestParam(defaultValue = "asc")String order,
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "2")int size){
        Page<Product> products = productService.getProductsSortedAndPaged(order,page,size);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<Page<Product>> searchProducts(
            @RequestParam String name,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size) {
        Page<Product> products = productService.searchProductsByName(name, order, page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
