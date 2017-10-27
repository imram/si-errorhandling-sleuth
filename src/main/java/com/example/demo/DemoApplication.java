package com.example.demo;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.AbstractMessageChannel;

@SpringBootApplication
@ImportResource(locations = "classpath*:integration-context.xml")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public SmartInitializingSingleton ecInterceptorConfigurer(AbstractMessageChannel errorChannel) {
	    return () -> errorChannel.addInterceptor(0, new SleuthWorkAroundInterceptor());
	}
}
