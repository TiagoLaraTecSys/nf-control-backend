package com.rich.nf_control;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.rich.nf_control.adapter.out.database")
@EnableJpaRepositories(basePackages = "com.rich.nf_control.adapter.out.database")
public class NfControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(NfControlApplication.class, args);
	}

}