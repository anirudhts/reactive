package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
  private Long customerId;
  private String name;
  private String phoneNo;
  private String city;
  private String email;
  private String address;
  private String addressLine1;
  private String addressLine2;

  public Customer(CustomerEntity customerEntity) {
    this.customerId = customerEntity.getCustomerId();
    this.name = customerEntity.getName();
    this.phoneNo = customerEntity.getPhoneNo();
    this.city = customerEntity.getCity();
    this.email = customerEntity.getEmail();
    this.address = customerEntity.getAddress();
    this.addressLine1 = customerEntity.getAddressLine1();
    this.addressLine2 = customerEntity.getAddressLine2();
  }
}
