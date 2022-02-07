package com.example.springbootgraphql.repository;

import client.domain.Customer;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
    Flux<Customer> findByName(String name);

    Mono<Void> deleteByName(String customerName);

    //@Query("select u from customer u where u.id = : customerId")
    //Customer findByCustomerId(@Param("customerId") Integer customerId);
}

