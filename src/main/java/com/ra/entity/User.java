package com.ra.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String email;
    private String passWord;
    private String phone;
    private String address;
    private  String role;
    private  Boolean status;
    @OneToMany(mappedBy = "user")
    private Set<Orders> orders;
}
