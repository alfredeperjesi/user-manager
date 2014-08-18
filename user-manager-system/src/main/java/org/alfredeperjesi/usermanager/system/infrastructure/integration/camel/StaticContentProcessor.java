package org.alfredeperjesi.usermanager.system.infrastructure.integration.camel;

import static org.alfredeperjesi.usermanager.system.infrastructure.integration.camel.ContentTypeHelper.getContentType;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.io.IOUtils;

public class StaticContentProcessor implements Processor {

    private static final String WEBAPP = "webapp/";

    @Override
    public void process(final Exchange exchange) throws Exception {
        String name = WEBAPP + exchange.getIn().getHeader(Exchange.HTTP_PATH);
        InputStream in = getClass().getClassLoader().getResourceAsStream(name);
        if (in != null) {
            String contentType = getContentType(name);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            IOUtils.copy(in, out);
            exchange.getOut().setHeader(Exchange.CONTENT_TYPE, contentType);
            exchange.getOut().setBody(out.toByteArray());
        }
    }
}