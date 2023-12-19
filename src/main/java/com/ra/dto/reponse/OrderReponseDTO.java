package com.ra.dto.reponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderReponseDTO {
    private Integer orderId;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date orderDate;
    private String name;
    private String email;
    private String phone;
    private String Address;
    private String comment;
    private Boolean status;
    private String userName;

}
