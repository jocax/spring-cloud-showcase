package com.jocax.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class SimpleRest {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SimpleClient simpleClient;


    @RequestMapping("/feign")
    public String feign() {
    	return simpleClient.get();
    }
    
    @RequestMapping("/rt")
    public String rt() {
    	return restTemplate.getForObject("http://simpleservice", String.class);
    }
    
}
