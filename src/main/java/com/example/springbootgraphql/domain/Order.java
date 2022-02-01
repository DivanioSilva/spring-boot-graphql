package com.example.springbootgraphql.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Builder
@Data
public class Order {
    @Id
    private Integer id;
    //private Integer customerId;
    private String productName;
    //private Customer customer;
}
