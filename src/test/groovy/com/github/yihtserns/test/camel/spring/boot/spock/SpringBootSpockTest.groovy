package com.github.yihtserns.test.camel.spring.boot.spock;

import java.util.concurrent.Callable;
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
@SpringApplicationConfiguration(classes = SpringBootSpockTest.Application.class)
class SpringBootSpockTest extends Specification {

    @Autowired
    Callable<String> greeter;

    def testName() {
        when:
        def result = greeter.call()

        then:
        result == 'Hi!'
    }

    @SpringBootApplication
    static class Application {

        @Bean
        public Callable<String> callable() {
            return new Callable<String>() {

                public String call() throws Exception {
                    return "Hi!";
                }
            };
        }
    }
}
