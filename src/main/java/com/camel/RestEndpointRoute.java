package com.camel;

import org.apache.camel.builder.RouteBuilder;

/**
 * Camel route which configures a rest endpoint on /api/.
 */
public class RestEndpointRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        /* Receives requests on '/api' */
        rest("/api")
                /* 'GET' on '/test' will be forwarded to the route 'direct:html-hello-world' */
                .get("/test")
                .to("direct:html-hello-world");
    }
}
