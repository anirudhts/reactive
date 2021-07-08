package com.example.controller;

import com.example.service.CustomerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import javax.inject.Inject;

@Controller("/customers")
public class CustomerController {

  private final CustomerService customerService;

  @Inject
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Get("/{customerId}")
  public HttpResponse get(@PathVariable Long customerId) {
    customerService.getCustomer(customerId);
    return HttpResponse.ok();
  }
}
