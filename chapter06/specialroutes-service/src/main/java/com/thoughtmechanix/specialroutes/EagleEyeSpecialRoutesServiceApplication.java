package com.thoughtmechanix.specialroutes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EagleEyeSpecialRoutesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EagleEyeSpecialRoutesServiceApplication.class, args);
	}
}
