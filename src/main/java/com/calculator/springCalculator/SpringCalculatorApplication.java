package com.calculator.springCalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//si anotacija yra lygi @Configuration, @EnableAutoConfiguration, @ComponentScan
//si anotacija nurodoma klaseje turincioje pagrindini main metoda
//Pagrindines klases negalima kelti i katalogus
@SpringBootApplication
public class SpringCalculatorApplication {

	public static void main(String[] args) {
		//programos kontrole deleguojama statiniam klases metodui RUN
		//nurodant aplikacijos saknini komponenta (root)
		//Spring karkasas (Framework) paleis aplikacija t.y. startuos tomcat serveri
		SpringApplication.run(SpringCalculatorApplication.class, args);

	}

}
