package com.example.controller;

import com.example.models.Customer;
import com.example.models.CustomerEntity;
import com.example.service.CustomerService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
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
              System.out.println("customer present" + customerEntity);
              Customer crust = new Customer(customerEntity);
              return crust;
            })
        .onErrorResume(
            (err) -> {
              System.out.println("customer error");
              err.printStackTrace();
              final Customer crust1 = new Customer();
              crust1.setName(err.getMessage());
              return Mono.just(crust1);
            })
        .defaultIfEmpty(crust2);
  }

  @Post("/")
  public Mono<CustomerEntity> post(@Body Customer customer) {
    return customerService.saveCustomer(customer);
  }
}
