package com.ra.service;

import com.ra.dto.reponse.OrderReponseDTO;
import com.ra.dto.request.OrderRequestDTO;
import com.ra.dto.request.ProductRequestDTO;
import com.ra.entity.OrderDetail;
import com.ra.entity.Orders;
import com.ra.entity.Product;
import com.ra.entity.User;
import com.ra.repository.OrderDetailRepository;
import com.ra.repository.OrderRepository;
import com.ra.repository.ProductRepository;
import com.ra.repository.UserRepository;
import com.ra.until.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<OrderReponseDTO> findAll() {
        return orderRepository.findAll().stream().map(order -> {
            OrderReponseDTO.OrderReponseDTOBuilder builder = OrderReponseDTO.builder()
                    .orderId(order.getOderId())
                    .orderDate(order.getOrderDate())
                    .name(order.getName())
                    .email(order.getEmail())
                    .phone(order.getPhone())
                    .Address(order.getAddress())
                    .comment(order.getComment())
                    .status(order.getStatus())
                    .userName(order.getUser().getUserName());
            return builder.build();
        }).toList();
    }

    @Override
    public Orders findById(Integer id) {
        return null;
    }

    @Override
    public Orders save(Orders orders) {
        return null;
    }

    @Override
    public void delete(Integer id) {
    }
    @Override
    public void addNewOrderByUserId(Integer id, OrderRequestDTO orderRequestDTO) throws CustomException {
        // Kiểm tra giá trị null cho orderRequestDTO và optionalProduct
        if (orderRequestDTO == null || orderRequestDTO.getProductId() == null) {
            throw new CustomException("Thông tin đơn hàng không hợp lệ");
        }
        Optional<User> optionalUser = userRepository.findById(id);
        int productId = orderRequestDTO.getProductId();
        Optional<Product> optionalProduct = productRepository.findById(productId);
//         kiểm tra user có tồn tại hay không, và  product có tồn tại hay không
        if (optionalUser.isPresent() && optionalProduct.isPresent()) {
            User user = optionalUser.get();

            Optional<Orders> optionalOrders = orderRepository.findByStatusAndUser(false,user);
            Orders savedOrder;
            if(optionalOrders.isPresent()){
                savedOrder = optionalOrders.get();
            }else {
                Orders newOrder = Orders.builder()
                        .orderDate(orderRequestDTO.getOrderDate())
                        .name(orderRequestDTO.getName())
                        .email(orderRequestDTO.getEmail())
                        .phone(orderRequestDTO.getPhone())
                        .Address(orderRequestDTO.getAddress())
                        .comment(orderRequestDTO.getComment())
                        .status(false)
                        .user(user)
                        .build();
                 savedOrder = orderRepository.save(newOrder);
            }

            Product product = optionalProduct.get();

            // kiểm tra xem sản phẩm tồn tại trong orderDetail chưa
            Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findByOrderAndProduct(savedOrder, product);
            if (optionalOrderDetail.isPresent()) {
                // Cập nhật thông tin OrderDetail nếu sản phẩm đã tồn tại
                OrderDetail existingOrderDetail = optionalOrderDetail.get();
                existingOrderDetail.setTotalQuantity(existingOrderDetail.getTotalQuantity() + 1);
                existingOrderDetail.setTotalPrice(existingOrderDetail.getTotalPrice() + product.getPrice());
                orderDetailRepository.save(existingOrderDetail);
            } else {
                // Nếu sản phẩm chưa tồn tại, tạo OrderDetail mới
                OrderDetail orderDetail = OrderDetail.builder()
                        .order(savedOrder)
                        .totalPrice(product.getPrice())
                        .totalQuantity(1)
                        .product(product)
                        .build();
                orderDetailRepository.save(orderDetail);
            }
        } else {
            throw new CustomException("Không tìm thấy người dùng hoặc sản phẩm");
        }
    }

}
