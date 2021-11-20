package com.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.platform.http.vertx.VertxPlatformHttpServer;
import org.apache.camel.component.platform.http.vertx.VertxPlatformHttpServerConfiguration;

/**
 * Rest configuration Camel route.
 * Receives a context, host and port which will define how and where a server will be created.
 * When not specified which component will be used to handle incoming HTTP requests, Camel Undertow will be used as fallback.
 * See {@link com.camel.RestComponent}.
 */
public class RestConfigRoute extends RouteBuilder {

    /**
     * Camel context instance object.
     */
    private final CamelContext context;
    /**
     * Camel DSL component definition.
     * See {@link com.camel.RestComponent}.
     */
    private final RestComponent component;
    /**
     * Server host.
     */
    private final String host;
    /**
     * Server port.
     */
    private final int port;
    private VertxPlatformHttpServerConfiguration vertxConfig;

    /**
     * @param context CamelContext instance.
     * @param host    Server host.
     * @param port    Server port.
     */
    public RestConfigRoute(CamelContext context, String host, int port) {
        this.context = context;
        this.component = RestComponent.UNDERTOW;
        this.host = host;
        this.port = port;
    }

    /**
     * @param context   CamelContext instance.
     * @param component Camel DSL component. See {@link com.camel.RestComponent}.
     * @param host      Server host.
     * @param port      Server port.
     */
    public RestConfigRoute(CamelContext context, RestComponent component, String host, int port) {
        this.context = context;
        this.component = component != null ? component : RestComponent.UNDERTOW;
        this.host = host;
        this.port = port;
    }

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component(component.getComponent())
                .host(host)
                .port(port);

        createVertxServiceWhenPlatformHttp();
    }

    /**
     * When platform-http is chosen as the DSL component, a vertx http server must be created.
     * A custom configuration can be defined using the vertxConfig() method.
     *
     * @throws Exception Java Exception.
     */
    private void createVertxServiceWhenPlatformHttp() throws Exception {
        if (component == RestComponent.PLATFORM_HTTP) {

            if (this.vertxConfig == null) {
                this.vertxConfig = new VertxPlatformHttpServerConfiguration();
                vertxConfig.setBindPort(port);
                vertxConfig.setBindHost(host);
            }

            context.addService(new VertxPlatformHttpServer(vertxConfig));

        }
    }

    public void vertxConfig(final VertxPlatformHttpServerConfiguration vertxConfig) {
        this.vertxConfig = vertxConfig;
    }

}
