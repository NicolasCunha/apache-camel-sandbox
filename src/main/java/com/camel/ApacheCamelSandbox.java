package com.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Main class to study and learn Apache Camel.
 * As always, read the docs! {@link <a href="https://camel.apache.org/docs/">...</a>}
 */
public class ApacheCamelSandbox {

    public static void main(String[] args) throws Exception {
        ApacheCamelSandbox sandbox = new ApacheCamelSandbox();
        sandbox.boot();
    }

    public void boot() throws Exception {
        /* Create an Apache Camel content. This object receives routes which will - according to its component - execute a sequence of steps.*/
        /* https://camel.apache.org/manual/routes.html */
        final CamelContext camelContext = new DefaultCamelContext();

        /* Create a route which will configure an HTTP server, running on 'localhost', port '10000'. */
        final RestConfigRoute configRoute = new RestConfigRoute(camelContext,
                RestComponent.PLATFORM_HTTP,
                "localhost",
                10000);

        /* Add the route to the context. */
        camelContext.addRoutes(configRoute);
        /* Create a simple rest endpoint route. */
        camelContext.addRoutes(new RestEndpointRoute());
        /* Create a route which will generate logs, files, and return 'Hello, world!' as 'text/html'.*/
        camelContext.addRoutes(new HtmlHelloWorldRoute());
        /* Create a route which will generate logs, and files with 'Hello, earth!' as content. */
        /* This route is wire-tapped from the HtmlHelloWorldRoute route. */
        camelContext.addRoutes(new JsonHelloWorldRoute());

        camelContext.start();

    }

}
