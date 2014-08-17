package org.alfredeperjesi.usermanager.system.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.alfredeperjesi.usermanager.system.application.ApplicationInitializer;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"org.alfredeperjesi.usermanager.system.infrastructure", "org.alfredeperjesi.usermanager.system.application"})
public class RootApplicationConfig extends CamelConfiguration {
    @Bean
    public JacksonJsonProvider jacksonJsonProvider() {
        return new JacksonJsonProvider();
    }

    @Bean(initMethod = "init")
    public ApplicationInitializer applicationInitializer() {
        return new ApplicationInitializer();
    }
}