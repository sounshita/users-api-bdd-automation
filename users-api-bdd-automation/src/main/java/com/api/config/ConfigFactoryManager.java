package com.api.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigFactoryManager {
    private static FrameworkConfig config;

    private ConfigFactoryManager() {}

    public static FrameworkConfig getConfig(){
        if(config==null){
            config= ConfigFactory.create(FrameworkConfig.class);
        }
        return config;
    }

}
