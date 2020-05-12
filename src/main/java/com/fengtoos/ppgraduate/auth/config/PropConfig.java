package com.fengtoos.ppgraduate.auth.config;

import com.fengtoos.ppgraduate.auth.prop.IgnoreUrlSettings;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropConfig {

    @Bean
    @ConfigurationProperties(prefix = "fengtoos.ignore")
    public IgnoreUrlSettings getFengtoosSettings(){
        return new IgnoreUrlSettings();
    }
}
