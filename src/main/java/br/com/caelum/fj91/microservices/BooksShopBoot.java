package br.com.caelum.fj91.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableCaching
@EnableCircuitBreaker
@EnableHystrixDashboard
@SpringBootApplication
public class BooksShopBoot {

    public static void main(String[] args) {
        SpringApplication.run(BooksShopBoot.class, args);
    }
}
