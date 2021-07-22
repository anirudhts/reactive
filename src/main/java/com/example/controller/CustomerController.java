package com.example.controller;

import com.example.models.Customer;
import com.example.models.CustomerEntity;
import com.example.service.CustomerService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import java.time.Duration;
import javax.inject.Inject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller("/customer")
public class CustomerController {

  private final CustomerService customerService;

  @Inject
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Get("/{customerId}")
  public Mono<Customer> get(@PathVariable Long customerId) {
    final Customer crust2 = new Customer();
    crust2.setName("Customer not there");
    System.out.println("curr thread before calling repo " + Thread.currentThread().getName());
    return customerService
        .getCustomer(customerId)
        .map( // goes into this if object is present
            customerEntity -> {
              System.out.println("customer present" + customerEntity);
              System.out.println("curr thread" + Thread.currentThread().getName());
              Customer crust = new Customer(customerEntity);
              return crust;
            })
        .onErrorResume(
            (err) -> { // if error occurs
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

  @Get("/db/{customerId}")
  public Mono<Customer> getFromDB(@PathVariable Long customerId) {
    return customerService.getCustomerFromDB(customerId);
  }

  @Get("/cache/{customerId}")
  public Mono<Customer> getFromCache(@PathVariable Long customerId) {
    return customerService.getCustomerFromCache(customerId);
  }

  @Get(value = "/stream", produces = MediaType.APPLICATION_JSON_STREAM)
  public Flux<Integer> getStreamOfElements() {
    return Flux.range(1, 5).delayElements(Duration.ofSeconds(1)).log();
  }
}
