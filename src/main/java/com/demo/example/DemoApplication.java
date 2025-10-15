package com.demo.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories; // <-- NEW
import org.springframework.boot.autoconfigure.domain.EntityScan; // <-- NEW

@SpringBootApplication
@ComponentScan(basePackages = { "com.demo.example", "com.model" })
// 추가: TestRepository가 있는 패키지 지정
@EnableJpaRepositories(basePackages = { "com.model.repository" })
// 추가: TestDB 엔티티가 있는 패키지 지정
@EntityScan(basePackages = { "com.model.domain" })
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}