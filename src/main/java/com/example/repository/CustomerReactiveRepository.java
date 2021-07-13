package com.example.repository;

import com.example.models.CustomerEntity;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import reactor.core.publisher.Mono;

@R2dbcRepository(dialect = Dialect.ORACLE)
public interface CustomerReactiveRepository
    extends ReactiveStreamsCrudRepository<CustomerEntity, Long> {

  @Executable
  Mono<CustomerEntity> findByCustomerId(Long customerId);
}
