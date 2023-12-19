package com.ra.service;
import com.ra.dto.reponse.OrderReponseDTO;
import com.ra.dto.request.OrderRequestDTO;
import com.ra.entity.Orders;
import com.ra.until.exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<OrderReponseDTO> findAll();
    Orders findById(Integer id);
    Orders save(Orders orders);
    void delete(Integer id);
    void addNewOrderByUserId(Integer id, OrderRequestDTO orderRequestDTO) throws CustomException;
}
