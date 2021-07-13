package com.example.controller;

import com.example.models.Customer;
import com.example.service.CustomerService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import javax.inject.Inject;
import reactor.core.publisher.Mono;

@Controller("/customers")
public class CustomerController {

  private final CustomerService customerService;

  @Inject
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Get("/{customerId}")
  public Mono<Customer> get(@PathVariable Long customerId) {
    final Customer crust2 = new Customer();
    crust2.setName("Anirudh");
    return customerService
        .getCustomer(customerId)
        .map(
            customerEntity -> {
              Customer crust = new Customer();
              crust.setCustomerId(customerEntity.getCustomerId());
              crust.setName(customerEntity.getName());
              return crust;
            })
        .onErrorResume(
            (err) -> {
              final Customer crust1 = new Customer();
              crust1.setName(err.getMessage());
              return Mono.just(crust1);
            })
        .defaultIfEmpty(crust2);
  }
}
