package com.camel;

/**
 * Utilities class.
 */
public class SandboxUtils {

    private SandboxUtils() {
        /* Avoid instantiation. */
    }

    /**
     * Create a simple route DSL using XML.
     * This route is a <a href="https://camel.apache.org/components/3.7.x/timer-component.html">timer</a>, which will log a message every second.
     *
     * @return String containing a XML DSL.
     */
    public static String getSampleRouteXmlDsl() {

        return "<route id=\"xml-log-route\">" +
                "  <from uri=\"timer://test_custom_route_xml?period=1s\"/>" +
                "  <log message=\"Hello, XML Timed Log!\"/>" +
                "</route>";

    }

    /**
     * Create a simple route DSL using YAML.
     * This route is a <a href="https://camel.apache.org/components/3.7.x/timer-component.html">timer</a>, which will log a message every second.
     *
     * @return String containing a YAML DSL.
     */
    public static String getSampleRouteYamlDsl() {
        return "- route:\n" +
                "    id: \"yaml-log-route\" \n" +
                "    from: \"timer://test_custom_route_yaml?period=1s\"\n" +
                "    steps:\n" +
                "      - log: \n" +
                "           message: \"Hello, YAML Timed Log!\"";
    }

}
