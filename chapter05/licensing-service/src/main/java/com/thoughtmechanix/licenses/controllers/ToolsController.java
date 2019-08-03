package com.thoughtmechanix.licenses.controllers;

import com.thoughtmechanix.licenses.services.DiscoveryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/tools")
public class ToolsController {

  @Autowired
  private DiscoveryService discoveryService;

  @GetMapping("/eureka/services")
  public List<String> getEurekaServices() {
    return discoveryService.getEurekaServices();
  }
}
