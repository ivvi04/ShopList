package ru.lakeevda.listproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ListProductServiceApplication {

    static void main(String[] args) {
        SpringApplication.run(ListProductServiceApplication.class, args);
    }

}
