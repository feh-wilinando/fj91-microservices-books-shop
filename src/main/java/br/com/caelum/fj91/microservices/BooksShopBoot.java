package br.com.caelum.fj91.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BooksShopBoot {

    public static void main(String[] args) {
        SpringApplication.run(BooksShopBoot.class, args);
    }
}
