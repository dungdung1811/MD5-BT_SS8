package com.ra.controller;
import com.ra.dto.reponse.OrderReponseDTO;
import com.ra.dto.request.OrderRequestDTO;
import com.ra.entity.Orders;
import com.ra.service.OrderService;
import com.ra.until.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/{idUser}")
    public ResponseEntity<Orders> createOrderByUserId(@PathVariable Integer idUser,@RequestBody OrderRequestDTO orderRequestDTO) throws CustomException {
       orderService.addNewOrderByUserId(idUser,orderRequestDTO);
       return  new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<OrderReponseDTO>> getAllOrder(){
       return new ResponseEntity<>(orderService.findAll(),HttpStatus.OK);
    }
}
