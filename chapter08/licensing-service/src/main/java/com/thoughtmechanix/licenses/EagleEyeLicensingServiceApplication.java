package com.thoughtmechanix.licenses;

import com.thoughtmechanix.licenses.events.models.OrganizationChangeModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableBinding(Sink.class)
public class EagleEyeLicensingServiceApplication {

	@StreamListener(Sink.INPUT)
	public void loggerSink(OrganizationChangeModel orgChange) {
		log.debug("Received an event for organization id {}", orgChange.getOrganizationId());
	}

	public static void main(String[] args) {
		SpringApplication.run(EagleEyeLicensingServiceApplication.class, args);
	}
}
