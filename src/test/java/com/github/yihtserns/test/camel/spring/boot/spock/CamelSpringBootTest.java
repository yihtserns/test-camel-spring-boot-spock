package com.github.yihtserns.test.camel.spring.boot.spock;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yihtserns
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CamelSpringBootTest.Application.class)
public class CamelSpringBootTest {

    @Autowired
    ProducerTemplate producerTemplate;

    @Test
    public void testName() throws Exception {
        String result = producerTemplate.requestBody("direct:start", (Object) null, String.class);
        assertThat(result, is("Called!"));
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
