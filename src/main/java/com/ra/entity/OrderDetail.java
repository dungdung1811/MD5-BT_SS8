package com.ra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int totalQuantity;
    private double totalPrice;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "oderId")
    private Orders order;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Product product;
}
