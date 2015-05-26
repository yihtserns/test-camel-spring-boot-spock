package com.github.yihtserns.test.camel.spring.boot.spock;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spock.lang.Specification
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author yihtserns
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CamelSpringBootTest.Application.class)
class CamelSpringBootSpockTest extends Specification {

    @Autowired
    ProducerTemplate producerTemplate;

    @Test
    public void testName() throws Exception {
        when:
        String result = producerTemplate.requestBody("direct:start", (Object) null, String.class);

        then:
        result == "Called!"
    }

    @SpringBootApplication
    static class Application {

        @Bean
        RoutesBuilder routeBuilder() {
            return new RouteBuilder() {

                @Override
                public void configure() throws Exception {
                    from("direct:start").transform(constant("Called!"));
                }
            };
        }
    }
}
