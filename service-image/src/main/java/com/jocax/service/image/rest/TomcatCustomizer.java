package com.jocax.service.image.rest;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

//@Component
public class TomcatCustomizer implements TomcatConnectorCustomizer {
    @Override
    public void customize(Connector connector) {
        connector.setProperty("compression", "on");
        // Add json and xml mime types, as they're not in the mimetype list by default
        connector.setProperty("compressableMimeType", "text/html,text/xml,text/plain,application/json,application/xml");
    }
}