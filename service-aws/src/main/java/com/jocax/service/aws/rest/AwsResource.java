package com.jocax.service.aws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/aws")
public class AwsResource {


    private final AwsInfo awsInfo;

    @Autowired
    public AwsResource(AwsInfo awsInfo) {
        this.awsInfo = awsInfo;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public AwsInfo info() {
        return this.awsInfo;
    }
}
