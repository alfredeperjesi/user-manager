package org.alfredeperjesi.usermanager.system.config;

import org.alfredeperjesi.usermanager.system.application.ApplicationInitializer;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Configuration
@ComponentScan({"org.alfredeperjesi.usermanager.system.infrastructure", "org.alfredeperjesi.usermanager.system.application"})
@Import(SwaggerConfig.class)
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