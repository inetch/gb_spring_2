package ru.gb.inetch.shoppee.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig extends WebMvcAutoConfiguration {
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        if (!registry.hasMappingForPattern("/webjars/**")) {
//            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//        }
//    }

    @Bean
    public ConnectionFactory getMQConnectionFactory(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        return factory;
    }
}
