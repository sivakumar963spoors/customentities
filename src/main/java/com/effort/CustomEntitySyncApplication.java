package com.effort;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.effort.forminterface")
@ComponentScan(basePackages = "com.effort")
public class CustomEntitySyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomEntitySyncApplication.class, args);
	}

}
