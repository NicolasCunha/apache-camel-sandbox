package com.camel;

/**
 * Enum which specifies which Camel DSL component should be used.
 * Used in {@link com.camel.RestConfigRoute} constructor.
 * See {@linktourl https://camel.apache.org/manual/rest-dsl.html}.
 */
public enum RestComponent {

    /**
     * See {@linktourl https://camel.apache.org/components/3.13.x/netty-http-component.html}
     */
    NETTY_HTTP("netty-http"),
    /**
     * See {@linktourl https://camel.apache.org/components/3.13.x/jetty-component.html}
     */
    JETTY("jetty"),
    /**
     * See {@linktourl https://camel.apache.org/components/3.13.x/undertow-component.html}
     */
    UNDERTOW("undertow"),
    /**
     * See {@linktourl https://camel.apache.org/components/3.13.x/platform-http-component.html}
     */
    PLATFORM_HTTP("platform-http");

    private final String camelComponent;

    RestComponent(final String camelComponent) {
        this.camelComponent = camelComponent;
    }

    public String getComponent() {
        return this.camelComponent;
    }


}
