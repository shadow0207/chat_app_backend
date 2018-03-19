package com.stackroute.activitystream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*
 * The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration 
 * and @ComponentScan with their default attributes
 */
@SpringBootApplication
@EnableEurekaClient
public class ActivityStreamStep6BackendApplication {

	   /*
	    * 
	    * You need to run SpringApplication.run, because this method start whole spring framework. 
	    * Code below integrates your main() with SpringBoot 
	    */
	public static void main(String[] args) {
		SpringApplication.run(ActivityStreamStep6BackendApplication.class, args);
	}
}
