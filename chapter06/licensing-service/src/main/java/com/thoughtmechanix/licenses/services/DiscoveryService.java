package com.thoughtmechanix.licenses.services;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscoveryService {

  private final DiscoveryClient discoveryClient;

  public List<String> getEurekaServices() {
    List<String> services = new ArrayList<>();

    discoveryClient.getServices().forEach(serviceName ->
        discoveryClient.getInstances(serviceName).forEach(instance ->
            services.add(String.format("%s:%s", serviceName, instance.getUri()))));

    return services;
  }
}
