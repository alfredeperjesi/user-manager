package org.alfredeperjesi.usermanager.system.infrastructure.integration.camel;

import com.google.common.collect.ImmutableMap;
import org.alfredeperjesi.usermanager.system.config.RootApplicationConfig;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;

import static org.alfredeperjesi.usermanager.system.Fixtures.ID;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {RootApplicationConfig.class},
        loader = CamelSpringDelegatingTestContextLoader.class
)
@MockEndpoints
public class UserManagerRouteBuilderTest {
    private static final String ENDPOINT_URI = "http://localhost:8091/UserManager/users";
    private static final String CREATE_USER_RESOURCE_JSON = "{\n" +
            "    \"type\":\"SUPER_USER\",\n" +
            "    \"firstName\":\"Super\",\n" +
            "    \"lastName\":\"User\",\n" +
            "    \"title\":\"super\",\n" +
            "    \"dateOfBirth\":\"1408148019\",\n" +
            "    \"emailAddress\":\"email@email.com\",\n" +
            "    \"password\":\"password\"\n" +
            "}";
    private static final Map<String, Object> headers = ImmutableMap.of("serviceUserId", (Object) ID);
    private static final int EXPECTED_COUNT = 1;
    private static final String MOCK_ENDPOINT_URI = "mock:http:localhost:8091/UserManager/users";

    @EndpointInject(uri = MOCK_ENDPOINT_URI)
    protected MockEndpoint cxfEndpoint;

    @Produce
    protected ProducerTemplate jettyProducer;

    @Test
    public void testRoute() throws InterruptedException {
        cxfEndpoint.expectedMessageCount(EXPECTED_COUNT);

        jettyProducer.sendBodyAndHeaders(ENDPOINT_URI, CREATE_USER_RESOURCE_JSON, headers);

        cxfEndpoint.assertIsSatisfied();
    }
}
