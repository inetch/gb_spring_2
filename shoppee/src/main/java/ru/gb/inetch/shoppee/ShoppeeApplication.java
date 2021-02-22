package ru.gb.inetch.shoppee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class ShoppeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppeeApplication.class, args);
    }

}
