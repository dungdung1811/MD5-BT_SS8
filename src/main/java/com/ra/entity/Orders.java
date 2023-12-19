package com.ra.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.Set;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oderId;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date orderDate;
    private String name;
    private String email;
    private String phone;
    private String Address;
    private String comment;
    private Boolean status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;
}
