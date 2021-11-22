package com.camel;

import org.apache.camel.ExtendedCamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.Resource;
import org.apache.camel.spi.RoutesLoader;
import org.apache.camel.support.ResourceHelper;

/**
 * Create a Camel route from a String.
 */
public class StringRoute extends RouteBuilder {

    private final String dsl;
    private final String extension;

    /**
     * Default constructor.
     *
     * @param dsl       String containing a Camel DSL.
     * @param extension DSL language, such as XML and YAML.
     */
    public StringRoute(final String dsl, final String extension) {
        this.dsl = dsl;
        this.extension = extension.startsWith(".") ? extension : ".".concat(extension);
    }

    @Override
    public void configure() throws Exception {

        final ExtendedCamelContext extendedCamelContext = getCamelContext().adapt(ExtendedCamelContext.class);
        final RoutesLoader routesLoader = extendedCamelContext.getRoutesLoader();
        final Resource resource = ResourceHelper.fromString("custom_route".concat(extension), dsl);
        routesLoader.loadRoutes(resource);

    }
}
