package com.camel;

/**
 * Enum which specifies which Camel DSL component should be used.
 * Used in {@link RestConfigRoute} constructor.
 * See {@link <a href="https://camel.apache.org/manual/rest-dsl.html">...</a>}.
 */
public enum RestComponent {

    /**
     * See {@link https://camel.apache.org/components/3.13.x/jetty-component.html}
     */
    JETTY("jetty"),
    /**
     * See {@link <a href="https://camel.apache.org/components/3.13.x/netty-http-component.html">...</a>}
     */
    NETTY_HTTP("netty-http"),
    /**
     * See {@link <a href="https://camel.apache.org/components/3.13.x/platform-http-component.html">...</a>}
     */
    PLATFORM_HTTP("platform-http"),
    /**
     * See {@link <a href="https://camel.apache.org/components/3.13.x/undertow-component.html">...</a>}
     */
    UNDERTOW("undertow");

    private final String camelComponent;

    RestComponent(final String camelComponent) {
        this.camelComponent = camelComponent;
    }

    public String getComponent() {
        return this.camelComponent;
    }


}
