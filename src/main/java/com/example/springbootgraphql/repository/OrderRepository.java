package com.example.springbootgraphql.repository;

import com.example.springbootgraphql.domain.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, Integer> {
}
