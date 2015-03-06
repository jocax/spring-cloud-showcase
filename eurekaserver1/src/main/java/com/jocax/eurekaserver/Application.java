package com.jocax.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.actuate.system.EmbeddedServerPortFileWriter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@ComponentScan
@Configuration
@EnableAutoConfiguration
@EnableEurekaServer
@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.addListeners(new ApplicationPidFileWriter("app.pid"));
        springApplication.addListeners(new EmbeddedServerPortFileWriter("app.port"));
        springApplication.run(args);
    }
    
}
