package com.ra.dto.reponse;

import jakarta.persistence.Entity;
import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserReponseDTO {
    private Integer id;
    private String userName;
    private String email;
    private String phone;
    private String address;
    private  String role;
}
