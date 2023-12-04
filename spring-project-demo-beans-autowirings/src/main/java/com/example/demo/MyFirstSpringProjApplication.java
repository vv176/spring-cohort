package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.DefaultLifecycleProcessor;
import org.springframework.stereotype.Component;

// Convention over Config, annotation
@SpringBootApplication
public class MyFirstSpringProjApplication {

	public static void main(String[] args) {
		ApplicationContext ctx =
		 SpringApplication.run(MyFirstSpringProjApplication.class, args);
	    for (String bean : ctx.getBeanDefinitionNames()) {
			System.out.println(bean);
		}
		System.out.println("=======");
		Object o = ctx.getBean("lifecycleProcessor");
		System.out.println(o.getClass().getName());
		GameManager gameManager =
				(GameManager)ctx.getBean("MyManager");
		gameManager.manage();
	}

}
// don't do anything on their own
// 1. Load all the Beans
// 2. Event Handling
