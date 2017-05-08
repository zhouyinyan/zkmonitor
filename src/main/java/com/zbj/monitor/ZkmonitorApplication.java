package com.zbj.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {})
public class ZkmonitorApplication {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "dev");
		SpringApplication.run(ZkmonitorApplication.class, args);
	}
}
