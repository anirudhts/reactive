package com.example.models;

import com.aerospike.mapper.annotations.AerospikeBin;
import com.aerospike.mapper.annotations.AerospikeKey;
import com.aerospike.mapper.annotations.AerospikeRecord;
import com.example.constants.AerospikeConstants;
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
@AerospikeRecord(namespace = AerospikeConstants.NAMESPACE, set = AerospikeConstants.CUSTOMER_SET)
public class CustomerEntity {

  @Id
  @MappedProperty("customer_id")
  @NotNull
  @AerospikeKey
  private Long customerId;

  @MappedProperty("customer_name")
  @AerospikeBin(name = "name")
  private String name;

  @Nullable
  @MappedProperty("phone_number")
  @AerospikeBin(name = "phone_number")
  private String phoneNo;

  @Nullable
  @MappedProperty("city")
  @AerospikeBin(name = "city")
  private String city;

  @Nullable
  @MappedProperty("email")
  @AerospikeBin(name = "email")
  private String email;

  @Nullable
  @MappedProperty("address")
  @AerospikeBin(name = "address")
  private String address;

  @Nullable
  @MappedProperty("address_line1")
  @AerospikeBin(name = "address_line1")
  private String addressLine1;

  @Nullable
  @MappedProperty("address_line2")
  @AerospikeBin(name = "address_line2")
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
