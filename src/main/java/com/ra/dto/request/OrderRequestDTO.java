package com.ra.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ra.entity.OrderDetail;
import com.ra.entity.Product;
import lombok.*;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private String phone;
    private String name;
    private String email;
    private String Address;
    private String comment;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date orderDate;
    private List<OrderDetail> orderDetail;
    private Integer productId;
}
