package com.project.rawflink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.project.rawflink")
public class RawFlink {
	public static void main(String[] args) {
		SpringApplication.run(RawFlink.class, args);
	}
}
