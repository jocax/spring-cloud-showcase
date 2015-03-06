package com.jocax.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.actuate.system.EmbeddedServerPortFileWriter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableAutoConfiguration
@EnableAdminServer
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setWebEnvironment(true);
        springApplication.addListeners(new ApplicationPidFileWriter("app.pid"));
        springApplication.addListeners(new EmbeddedServerPortFileWriter("app.port"));
        springApplication.run(args);
    }

}