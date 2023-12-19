package com.ra.repository;

import com.ra.entity.Orders;
import com.ra.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {
    Optional<Orders> findByStatusAndUser(Boolean status, User user);
}
