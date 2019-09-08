package com.thoughtmechanix.zuulsvr.model;

import java.util.Random;

public class AbTestingRoute {

  private String serviceName;
  private String active;
  private String endpoint;
  private Integer weight;

  public boolean useSpecialRoute(int maxThreshold) {
    if (!isActive()) {
      return false;
    }

    return weight < new Random().nextInt(maxThreshold) + 1;
  }

  public boolean isActive() {
    return active.equals("Y");
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getActive() {
    return active;
  }

  public void setActive(String active) {
    this.active = active;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }
}
