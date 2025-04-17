package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.demo.repository")
@EnableDiscoveryClient
public class SupplierApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplierApplication.class, args);
	}

}
