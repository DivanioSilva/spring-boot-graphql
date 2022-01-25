package com.example.springbootgraphql.domain;

import lombok.Builder;

@Builder
public class InputOrder {
    //@Id
    private Integer id;
    private Integer customerId;

    public InputOrder(Integer id, Integer customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
