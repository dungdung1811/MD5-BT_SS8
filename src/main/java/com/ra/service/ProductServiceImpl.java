package com.ra.service;

import com.ra.entity.Product;
import com.ra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> sortByPrice(String order) {
        if(order.equals("desc")){
            return productRepository.findAll(Sort.by("price").descending());
        }else if (order.equals("asc")){
            return productRepository.findAll(Sort.by("price").ascending());
        }
        return productRepository.findAll();

    }

    @Override
    public Page<Product> getProductsSortedAndPaged(String order, int pageNumber, int pageSize) {
        Pageable pageable;
        if (order.equals("asc")){
            pageable = PageRequest.of(pageNumber,pageSize,Sort.by("price").ascending());
            return productRepository.findByStatusTrueOrderByPriceAsc(pageable);
        } else if (order.equals("desc")){
            pageable = PageRequest.of(pageNumber,pageSize,Sort.by("price").descending());
            return productRepository.findByStatusTrueOrderByPriceDesc(pageable);
        } else {

             pageable =PageRequest.of(pageNumber,pageSize);
             return productRepository.findByStatusTrue(pageable);

        }

    }

    @Override
    public Page<Product> searchProductsByName(String name, String order, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageSize, pageNumber, Sort.unsorted());
        return productRepository.findByNameContainingAndStatusTrue(name,pageable);

    }


}
