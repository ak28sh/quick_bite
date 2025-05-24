package com.QuickBite.QuickBite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@ComponentScan("com.QuickBite.QuickBite.service")
//@ComponentScan("com.QuickBite.QuickBite.model")
//@ComponentScan("com.QuickBite.QuickBite.repository")
//@ComponentScan("com.QuickBite.QuickBite.config")
//@ComponentScan("com.QuickBite.QuickBite.controller")
//@ComponentScan("com.QuickBite.QuickBite.request")
//@ComponentScan("com.QuickBite.QuickBite.response")
//@ComponentScan("com.QuickBite.QuickBite.dto")
//@ComponentScan("com.QuickBite.QuickBite.Exception")


@EnableTransactionManagement
public class QuickBiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickBiteApplication.class, args);
	}

}
