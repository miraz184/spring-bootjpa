package com.gltraining.springbootdemo;

import com.gltraining.springbootdemo.frontend.FrontEnd;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringbootdemoApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringbootdemoApplication.class, args);
		FrontEnd frontEnd = context.getBean(FrontEnd.class);
		frontEnd.runUI();
	}
}
