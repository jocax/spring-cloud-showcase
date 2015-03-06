package com.jocax.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.actuate.system.EmbeddedServerPortFileWriter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableFeignClients
@Controller
@EnableZuulProxy
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setWebEnvironment(true);
        springApplication.addListeners(new ApplicationPidFileWriter("app.pid"));
        springApplication.addListeners(new EmbeddedServerPortFileWriter("app.port"));
        springApplication.run(args);
    }

}