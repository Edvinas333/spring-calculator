package com.calculator.springCalculator;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//servlet yra java programa veikianti webserveryje
//servlet paleidziamas kai vartotojas paspaudzia nuoroda,pateikia forma ar atlieka kitus veiksmus web
//kiekviena kliento uzklausa(req) praeina per Servlet kuris ji perduoda kontrolerio req mapping atributui

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringCalculatorApplication.class);
	}

}
