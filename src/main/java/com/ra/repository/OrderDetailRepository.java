package com.ra.repository;

import com.ra.entity.OrderDetail;
import com.ra.entity.Orders;
import com.ra.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    Optional<OrderDetail> findByOrderAndProduct(Orders orders, Product product);

}
