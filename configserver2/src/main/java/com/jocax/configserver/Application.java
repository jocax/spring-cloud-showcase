package com.jocax.configserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.actuate.system.EmbeddedServerPortFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.ConfigServerProperties;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.SpringApplicationEnvironmentRepository;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableConfigServer
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setWebEnvironment(true);
        springApplication.addListeners(new ApplicationPidFileWriter("app.pid"));
        springApplication.addListeners(new EmbeddedServerPortFileWriter("app.port"));
        springApplication.run(args);
    }

    @Configuration
    @Profile("file")
    protected static class SpringApplicationConfiguration {
        @Value("${spring.cloud.config.server.uri}")
        String locations;

        @Bean
        public ConfigServerProperties configServerProperties() {
            return new ConfigServerProperties();
        }

        @Bean
        public SpringApplicationEnvironmentRepository repository() {
            SpringApplicationEnvironmentRepository repo = new SpringApplicationEnvironmentRepository();
            repo.setSearchLocations(locations);
            return repo;
        }
    }
}