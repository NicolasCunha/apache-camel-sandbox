package com.camel;

import org.apache.camel.builder.RouteBuilder;

/**
 * Camel route which returns 'Hello, world!' in 'text/html', generate files and logs.
 */
public class HtmlHelloWorldRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        /* 'Direct' component provides direct, synchronous invocation of any consumers
                when a producer sends a message exchange. */
        /* https://camel.apache.org/components/3.7.x/direct-component.html */
        from("direct:html-hello-world")
                /* Set 'Content-Type' header as 'text/html'. */
                .setHeader("Content-Type", constant("text/html"))
                /* Write a log which will show the message ID and body. */
                .log("Request ${id}, Body: ${body}")
                /* Forward the message to 'log' component named 'html-hello-world-log'. */
                /* This is different from using log() method, since the log() method */
                /* will use an underlying SLF4J config to determine where and how logs will be written. */
                /* https://camel.apache.org/components/3.12.x/log-component.html */
                .to("log:html-hello-world-log-input")
                /* Open a choice condition. */
                .choice()
                    /* If the message body is not null */
                    .when(simple("${body} != null"))
                        /* Forward the message to the 'file' component.*/
                        /* The filename will be 'input_<ID>', where ID is the message id. */
                        /* https://camel.apache.org/components/3.12.x/file-component.html */
                        .to("file:message-files?fileName=input_${id}")
                    /* Else */
                    .otherwise()
                        /* Write a log saying that since the body is null, a file can't be created. */
                        .log("Message body is null, can't generate a file.")
                /* The exchange object contains data related to the request/response. */
                /* Using its 'message' property, set the body as 'Hello, world!' */
                .process(exchange -> exchange.getMessage().setBody(("Hello, world!")))
                /* Write a log which will show the message ID and body. */
                .log("Response ${id}, Body: ${body}")
                /* Forward the message to 'log' component named 'html-hello-world-log'. */
                .to("log:html-hello-world-log-output")
                /* Forward the message to 'file' component under directory 'message-output'. */
                /* The filename will be 'output_<ID>', where ID is the message id. */
                /* https://camel.apache.org/components/3.12.x/file-component.html */
                .to("file:message-files?fileName=output_${id}")
                .log("Wire-tapping message to another route.")
                /* Wire-tap forwards the request in parallel. */
                /* https://camel.apache.org/components/next/eips/wireTap-eip.html */
                .wireTap("direct:json-hello-world");
    }
}
