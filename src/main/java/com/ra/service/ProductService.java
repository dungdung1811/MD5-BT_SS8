package com.ra.service;

import com.ra.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<Product> sortByPrice(String order);
    Page<Product> getProductsSortedAndPaged(String order,int pageNumber,int pageSize);
    Page<Product> searchProductsByName(String name, String order, int pageNumber, int pageSize);

}
