package com.jocax.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("simpleservice")
public interface SimpleClient {
	
    @RequestMapping(method = RequestMethod.GET, value = "/")
    String get();

}

