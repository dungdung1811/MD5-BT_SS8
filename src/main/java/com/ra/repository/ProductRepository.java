package com.ra.repository;

import com.ra.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Page<Product> findByStatusTrue(Pageable pageable);

    Page<Product> findByStatusTrueOrderByPriceAsc(Pageable pageable);

    Page<Product> findByStatusTrueOrderByPriceDesc(Pageable pageable);
    Page<Product> findByNameContainingAndStatusTrue(String name,Pageable pageable);
}
