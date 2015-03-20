package com.jocax.service.aws.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AwsInfo {

    @Value("${ami-id:N/A}")
    private String amiId;

    @Value("${hostname:N/A}")
    private String hostname;

    @Value("${instance-type:N/A}")
    private String instanceType;

    @Value("${services/domain:N/A}")
    private String serviceDomain;

    public String getAmiId() {
        return this.amiId;
    }

    public String getHostname() {
        return this.hostname;
    }

    public String getInstanceType() {
        return this.instanceType;
    }

    public String getServiceDomain() {
        return this.serviceDomain;
    }
}
