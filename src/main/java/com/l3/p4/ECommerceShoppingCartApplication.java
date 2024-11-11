package com.l3.p4;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ECommerceShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceShoppingCartApplication.class, args);
	}
     @Bean
     public ModelMapper modelMapper() {
         return new ModelMapper();
     }
}
