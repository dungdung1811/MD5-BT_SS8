package com.ra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String name;
    private String description;
    private double price;
    private boolean status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    private Category category;
    private String images;
    private int stock;
    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderdetails;
}
