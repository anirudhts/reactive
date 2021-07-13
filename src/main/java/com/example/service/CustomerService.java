package com.example.service;

import com.example.models.Customer;
import com.example.models.CustomerEntity;
import com.example.repository.CustomerAerospikeReactiveRepository;
import com.example.repository.CustomerReactiveRepository;
import javax.inject.Inject;
import javax.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class CustomerService {

  private final CustomerReactiveRepository customerReactiveRepository;
  private final CustomerAerospikeReactiveRepository customerAerospikeReactiveRepository;

  @Inject
  public CustomerService(
      CustomerReactiveRepository customerReactiveRepository,
      CustomerAerospikeReactiveRepository customerAerospikeReactiveRepository) {
    this.customerReactiveRepository = customerReactiveRepository;
    this.customerAerospikeReactiveRepository = customerAerospikeReactiveRepository;
  }

  public Mono<CustomerEntity> getCustomer(Long customerId) {

    return customerAerospikeReactiveRepository.findByCustomerId(customerId);

    //    Mono<CustomerEntity> monoCustomerEntity =
    //        customerReactiveRepository
    //            .findByCustomerId(customerId)
    //            .then(Mono.error(new IllegalArgumentException("I am pushing the error")));
    //    System.out.println("Mono object vandhirukku " + monoCustomerEntity);
    //
    //    return monoCustomerEntity;
  }

  public Mono<CustomerEntity> saveCustomer(Customer customer) {
    return customerAerospikeReactiveRepository.saveCustomer(
        CustomerEntity.buildCustomerEntityFromCustomer(customer));
  }
}
