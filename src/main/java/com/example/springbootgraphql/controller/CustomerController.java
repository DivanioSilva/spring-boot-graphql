package com.example.springbootgraphql.controller;

import com.example.springbootgraphql.domain.Customer;
import com.example.springbootgraphql.domain.InputOrder;
import com.example.springbootgraphql.domain.Order;
import com.example.springbootgraphql.repository.CustomerRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    private final CustomerRepository customerRepository;
    private List<Order> orders;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.orders = new ArrayList<>();
    }

    @SchemaMapping(typeName = "Customer")
    Flux<Order> orders(Customer customer){
        if(this.orders.isEmpty()){
            for (int orderid = 1; orderid <= (Math.random() * 100); orderid++) {
                orders.add(Order.builder().id(orderid).customerId(customer.getId()).build());
            }
        }
        return Flux.fromIterable(orders);
    }

    @QueryMapping
    Flux<Customer> customers(){
        return this.customerRepository.findAll();
    }

    @QueryMapping
    Flux<Customer> customersByName(@Argument String name){
        return this.customerRepository.findByName(name);
    }

    @MutationMapping
    Mono<Customer> addCustomer(@Argument String name){
        return this.customerRepository.save(Customer.builder().name(name).build());
    }

    @MutationMapping
    Mono<Void> deleteCustomerById(@Argument Integer customerId){
        return this.customerRepository.deleteById(customerId);
    }

    @MutationMapping
    Mono<Void> deleteCustomerByName(@Argument String customerName){
        return this.customerRepository.deleteByName(customerName);
    }

    @MutationMapping
    Mono<Customer> updateCustomer(@Argument Integer customerId, @Argument String customerName){
        //Customer byCustomerId = this.customerRepository.findByCustomerId(customerId);
        //Mono<Customer> customer = this.customerRepository.findById(customerId);
        //customer.name(customerName);

        return this.customerRepository.save(Customer.builder().id(customerId).name(customerName).build());
    }

    @MutationMapping
    Mono<Order> addOrder(@Argument InputOrder inputOrder){
        Order order = Order.builder().id(inputOrder.getId()).customerId(inputOrder.getCustomerId()).build();
        this.orders.add(order);
        return Mono.just(order);
    }

}
