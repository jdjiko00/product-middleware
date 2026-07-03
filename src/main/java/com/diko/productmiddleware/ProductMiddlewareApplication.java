package com.diko.productmiddleware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ProductMiddlewareApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMiddlewareApplication.class, args);
	}

}
