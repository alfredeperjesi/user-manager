package org.alfredeperjesi.usermanager.system.infrastructure.integration.camel;

import org.apache.camel.Exchange;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserManagerRouteBuilder extends SpringRouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jetty:http://127.0.0.1:8091?matchOnUriPrefix=true")
                .choice()
                .when(header(Exchange.HTTP_PATH).startsWith("docs"))
                .process(new StaticContentProcessor())
                .when(header(Exchange.HTTP_PATH).startsWith("api"))
                .to("cxfbean:apiListingResourceJson?providers=jacksonJsonProvider,resourceListingProvider,apiDeclarationProvider")
                .otherwise()
                .to("cxfbean:userManagerRestService?providers=jacksonJsonProvider,clientErrorExceptionMapper");
    }
}
