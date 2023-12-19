package com.ra.dto.request;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
        private double price;
        private int quantity;
}
