package com.example.springbootgraphql.controller;

import com.example.springbootgraphql.domain.Customer;
import com.example.springbootgraphql.domain.Order;
import com.example.springbootgraphql.repository.CustomerRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @SchemaMapping(typeName = "Customer")
    Flux<Order> orders(Customer customer){
        List<Order> orders = new ArrayList<>();
        for (int orderid = 1; orderid <= (Math.random() * 100); orderid++) {
            orders.add(Order.builder().id(orderid).customerId(customer.getId()).build());
            //orders.add(new Order(orderid, customer.getId()));
        }
        return Flux.fromIterable(orders);
    }

    @QueryMapping
    Flux<Customer> customers(){
        return this.customerRepository.findAll();
    }
}
