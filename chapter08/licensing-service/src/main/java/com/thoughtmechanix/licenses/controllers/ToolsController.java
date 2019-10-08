package com.thoughtmechanix.licenses.controllers;

import com.thoughtmechanix.licenses.services.DiscoveryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/tools")
public class ToolsController {

  private final DiscoveryService discoveryService;

  @GetMapping("/eureka/services")
  public List<String> getEurekaServices() {
    return discoveryService.getEurekaServices();
  }
}
