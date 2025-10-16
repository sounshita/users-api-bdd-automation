package com.api.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:frameworkconfig.properties"})
public interface FrameworkConfig extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("timeout")
    @DefaultValue("5000")
    int timeout();

    @Key("environment")
    @DefaultValue("qa")
    String environment();

    @Key("report.name")
    @DefaultValue("Automation Report")
    String reportName();
}
