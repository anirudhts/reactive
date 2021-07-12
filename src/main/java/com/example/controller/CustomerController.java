package com.example.controller;

import com.example.models.CustomerEntity;
import com.example.service.CustomerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

@Controller("/customers")
public class CustomerController {

  private final CustomerService customerService;

  @Inject
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Get("/{customerId}")
  public Mono<CustomerEntity> get(@PathVariable Long customerId) {
    return customerService.getCustomer(customerId);
  }
}
