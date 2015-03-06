package com.jocax.simpleservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.actuate.system.EmbeddedServerPortFileWriter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient

@RestController
public class Application {

	@Value("${eureka.instance.metadataMap.instanceId}")
	private String instanceId;

    @Value("${simpleservice.message:}")
    private String message;

    @RequestMapping("/")
    public String home() {
        return "Simple service. Instance: @"+instanceId + ", message: " + message;
    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setWebEnvironment(true);
        springApplication.addListeners(new ApplicationPidFileWriter("app.pid"));
        springApplication.addListeners(new EmbeddedServerPortFileWriter("app.port"));
        springApplication.run(args);
    }

}