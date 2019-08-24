package com.thoughtmechanix.specialroutes.services;

import com.thoughtmechanix.specialroutes.exception.NoRouteFound;
import com.thoughtmechanix.specialroutes.model.AbTestingRoute;
import com.thoughtmechanix.specialroutes.repository.AbTestingRouteRepository;
import org.springframework.stereotype.Service;

@Service
public class AbTestingRouteService {

  private AbTestingRouteRepository abTestingRouteRepository;

  public AbTestingRouteService(AbTestingRouteRepository abTestingRouteRepository) {
    this.abTestingRouteRepository = abTestingRouteRepository;
  }

  public AbTestingRoute getRoute(String serviceName) {
    AbTestingRoute route = abTestingRouteRepository.findByServiceName(serviceName);

    if (route == null) {
      throw new NoRouteFound();
    }

    return route;
  }

  public void saveAbTestingRoute(AbTestingRoute route) {
    abTestingRouteRepository.save(route);
  }

  public void updateAbTestingRoute(AbTestingRoute route) {
    abTestingRouteRepository.save(route);
  }

  public void deleteAbTestingRoute(AbTestingRoute route) {
    abTestingRouteRepository.delete(route);
  }
}
