package com.example.models;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedEntity("customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerEntity {

  @Id
  @MappedProperty("customer_id")
  @NotNull
  private Long customerId;

  @MappedProperty("customer_name")
  private String name;

  @Nullable
  @MappedProperty("phone_number")
  private String phoneNo;

  @Nullable
  @MappedProperty("city")
  private String city;

  @Nullable
  @MappedProperty("email")
  private String email;

  @Nullable
  @MappedProperty("address")
  private String address;

  @Nullable
  @MappedProperty("address_line1")
  private String addressLine1;

  @Nullable
  @MappedProperty("address_line2")
  private String addressLine2;

  public static CustomerEntity buildCustomerEntityFromCustomer(Customer customer) {
    return new CustomerEntity(
        customer.getCustomerId(),
        customer.getName(),
        customer.getPhoneNo(),
        customer.getCity(),
        customer.getEmail(),
        customer.getAddress(),
        customer.getAddressLine1(),
        customer.getAddressLine2());
  }
}
