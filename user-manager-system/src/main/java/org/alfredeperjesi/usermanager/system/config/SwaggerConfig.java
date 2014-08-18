package org.alfredeperjesi.usermanager.system.config;

import org.alfredeperjesi.usermanager.api.UserManagerApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wordnik.swagger.jaxrs.config.BeanConfig;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;

@Configuration
public class SwaggerConfig {
    @Bean
    public BeanConfig swaggerConfig() {
        final BeanConfig config = new BeanConfig();

        config.setVersion("1.0.0");
        config.setScan(true);
        config.setResourcePackage(UserManagerApi.class.getPackage().getName());
        config.setBasePath("http://127.0.0.1:8091");

        return config;
    }

    @Bean
    public ApiDeclarationProvider apiDeclarationProvider() {
        return new ApiDeclarationProvider();
    }

    @Bean
    public ApiListingResourceJSON apiListingResourceJson() {
        return new ApiListingResourceJSON();
    }

    @Bean
    public ResourceListingProvider resourceListingProvider() {
        return new ResourceListingProvider();
    }

}