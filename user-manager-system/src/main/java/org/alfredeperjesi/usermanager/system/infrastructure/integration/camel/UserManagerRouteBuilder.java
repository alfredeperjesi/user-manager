package org.alfredeperjesi.usermanager.system.infrastructure.integration.camel;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserManagerRouteBuilder extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jetty:http://127.0.0.1:8091?matchOnUriPrefix=true")
                .to("cxfbean:userManagerRestService?providers=jacksonJsonProvider,clientErrorExceptionMapper");
    }
}
