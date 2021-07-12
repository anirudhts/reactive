package com.example.service;

import com.example.models.CustomerEntity;
import com.example.repository.CustomerReactiveRepository;
import javax.inject.Inject;
import javax.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class CustomerService {

  private final CustomerReactiveRepository customerReactiveRepository;

  @Inject
  public CustomerService(CustomerReactiveRepository customerReactiveRepository) {
    this.customerReactiveRepository = customerReactiveRepository;
  }

  public Mono<CustomerEntity> getCustomer(Long customerId) {

    Mono<CustomerEntity> monoCustomerEntity =
        customerReactiveRepository.findByCustomerId(customerId);
    System.out.println("Mono object vandhirukku " + monoCustomerEntity);

    return   monoCustomerEntity;}
}
