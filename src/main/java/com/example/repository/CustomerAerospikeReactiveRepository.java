package com.example.repository;

import com.aerospike.mapper.tools.ReactiveAeroMapper;
import com.example.models.CustomerEntity;
import javax.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class CustomerAerospikeReactiveRepository {

  private final ReactiveAeroMapper reactiveAeroMapper;

  public CustomerAerospikeReactiveRepository(ReactiveAeroMapper reactiveAeroMapper) {
    this.reactiveAeroMapper = reactiveAeroMapper;
  }

  public Mono<CustomerEntity> findByCustomerId(Long customerId) {
    return reactiveAeroMapper.read(CustomerEntity.class, customerId);
  }

  public Mono<CustomerEntity> saveCustomer(CustomerEntity customerEntity) {
    return reactiveAeroMapper.save(customerEntity);
  }
}
