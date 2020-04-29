package com.center.shiksha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.center.shiksha.model"})
public class ShikshaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShikshaApplication.class, args);
	}

}
