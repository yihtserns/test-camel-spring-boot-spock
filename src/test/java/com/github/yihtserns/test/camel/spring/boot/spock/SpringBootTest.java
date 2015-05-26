package com.github.yihtserns.test.camel.spring.boot.spock;

import java.util.concurrent.Callable;
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
@SpringApplicationConfiguration(classes = SpringBootTest.Application.class)
public class SpringBootTest {

    @Autowired
    Callable<String> callable;

    @Test
    public void testName() throws Exception {
        assertThat(callable.call(), is("Hi!"));
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
