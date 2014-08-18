package org.alfredeperjesi.usermanager.system.infrastructure.integration.camel;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StaticContentProcessorTest {

    private static final String MISSING_STATIC_RESOURCE_NAME = "missing";

    private static final String EXISTING_STATIC_RESOURCE_NAME = "docs/index.html";

    private static final String TEXT_HTML = "text/html";

    @Mock
    private Exchange exchange;

    @Mock
    private Message inMessage;

    @Mock
    private Message outMessage;

    private StaticContentProcessor staticContentProcessor;

    @Before
    public void setUp() {
        staticContentProcessor = new StaticContentProcessor();
    }

    @Test
    public void processDoesNotWriteToTheOutputWhenStaticResourceIsMissing() throws Exception {
        when(exchange.getIn()).thenReturn(inMessage);
        when(inMessage.getHeader(Exchange.HTTP_PATH)).thenReturn(MISSING_STATIC_RESOURCE_NAME);

        staticContentProcessor.process(exchange);

        verify(exchange, never()).getOut();
    }

    @Test
    public void processWritesToTheOutputWhenStaticResourceIsFound() throws Exception {
        when(exchange.getIn()).thenReturn(inMessage);
        when(exchange.getOut()).thenReturn(outMessage);
        when(inMessage.getHeader(Exchange.HTTP_PATH)).thenReturn(EXISTING_STATIC_RESOURCE_NAME);

        staticContentProcessor.process(exchange);

        verify(outMessage).setHeader(Exchange.CONTENT_TYPE, TEXT_HTML);
        verify(outMessage).setBody(anyObject());
    }
}
