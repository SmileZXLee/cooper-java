package cn.zxlee.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cn.zxlee")
public class CooperTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(cn.zxlee.test.CooperTestApplication.class, args);
    }

}

