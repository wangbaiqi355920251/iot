package io.iti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

//TODO Spring Cloud 解耦用
//@EnableDiscoveryClient
//@EnableAspectJAutoProxy
//@EnableAutoConfiguration
//@Configuration
//@ComponentScan(basePackageClasses = {NettyApplication.class})
@SpringBootApplication
public class NettyApplication {
	private final static Logger LOGGER = LoggerFactory.getLogger(NettyApplication.class);
	public static void main(String[] args) {
		LOGGER.info("NettyApplication started.");
		SpringApplication.run(NettyApplication.class, args);
	}
}
