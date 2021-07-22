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
    return customerAerospikeReactiveRepository
        .findByCustomerId(customerId)
        .log()
        .switchIfEmpty(
            customerReactiveRepository
                .findByCustomerId(customerId)
                //                            .doOnNext(customerEntity -> {
                //
                // customerAerospikeReactiveRepository.saveCustomer(customerEntity).log().subscribe();
                //                            }).log()
                .flatMap(
                    customerEntity -> {
                      System.out.println(
                          "Flat map just before execution " + Thread.currentThread().getName());
                      return customerAerospikeReactiveRepository
                          .saveCustomer(customerEntity)
                          .map(
                              (customerEntity1 -> {
                                System.out.println(
                                    "Flat map executing" + Thread.currentThread().getName());
                                return customerEntity1;
                              }));
                    })
                .log());

    //    return customerAerospikeReactiveRepository.findByCustomerId(customerId)
    //            .log()
    //            .map()
    //            .switchIfEmpty(customerReactiveRepository.findByCustomerId(customerId))
    //            .map(c)
    //            .switchIfEmpty(customerReactiveRepository.findByCustomerId(customerId))
    //            .doOnSuccess((customer)->customerReactiveRepository.save(customer));

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

  public Mono<Customer> getCustomerFromDB(Long customerId) {
    return customerReactiveRepository.findByCustomerId(customerId).map(Customer::new);
  }

  public Mono<Customer> getCustomerFromCache(Long customerId) {
    return customerAerospikeReactiveRepository.findByCustomerId(customerId).map(Customer::new);
  }
}
