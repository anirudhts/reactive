package com.example.config;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.async.NioEventLoops;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.client.reactor.AerospikeReactorClient;
import com.aerospike.mapper.tools.AeroMapper;
import com.aerospike.mapper.tools.ReactiveAeroMapper;
import io.micronaut.context.annotation.Factory;
import javax.inject.Singleton;

@Factory
public class AerospikeConfig {

  @Singleton
  public AerospikeClient aerospikeClient(AerospikeProperties aerospikeProperties) {
    ClientPolicy policy = new ClientPolicy();
    policy.eventLoops = new NioEventLoops(1);
    return new AerospikeClient(
        policy, aerospikeProperties.getHostname(), aerospikeProperties.getPort());
  }

  @Singleton
  public WritePolicy aerospikeWritePolicy() {
    WritePolicy writePolicy = new WritePolicy();
    writePolicy.setTimeout(10000);
    return writePolicy;
  }

  @Singleton
  public AeroMapper aerospikeMapper(AerospikeClient aerospikeClient) {
    return new AeroMapper.Builder(aerospikeClient).build();
  }

  @Singleton
  public AerospikeReactorClient aerospikeReactorClient(AerospikeClient aerospikeClient) {
    return new AerospikeReactorClient(aerospikeClient);
  }

  @Singleton
  public ReactiveAeroMapper reactiveAeroMapper(AerospikeReactorClient aerospikeReactorClient) {
    return new ReactiveAeroMapper.Builder(aerospikeReactorClient).build();
  }
}
