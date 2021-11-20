package com.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.builder.RouteBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Camel route which returns 'Hello, earth!' in 'text/html', generate files and logs.
 */
public class JsonHelloWorldRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        final Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("msg", "Hello, earth!");

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValueAsString(jsonBody);

        from("direct:json-hello-world")
                .log("Received message on json-hello-world route.")
                .setHeader("Content-Type", constant("application/json"))
                .process(exchange -> exchange
                        .getMessage()
                        .setBody(mapper.writeValueAsString(jsonBody)))
                .log("Message body: ${body}.")
                .log("Creating file on 'message-json' directory named 'json_${id}'.")
                .to("file:message-json?fileName=json_${id}");
    }
}
