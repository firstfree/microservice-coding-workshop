package com.thoughtmechanix.specialroutes.controllers;

import com.thoughtmechanix.specialroutes.model.AbTestingRoute;
import com.thoughtmechanix.specialroutes.services.AbTestingRouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/route")
public class SpecialRoutesServiceController {

  private AbTestingRouteService abTestingRouteService;

  public SpecialRoutesServiceController(AbTestingRouteService abTestingRouteService) {
    this.abTestingRouteService = abTestingRouteService;
  }

  @GetMapping("/abtesting/{serviceName}")
  public AbTestingRoute getAbTestingRoute(@PathVariable("serviceName") String serviceName) {
    return abTestingRouteService.getRoute(serviceName);
  }
}
