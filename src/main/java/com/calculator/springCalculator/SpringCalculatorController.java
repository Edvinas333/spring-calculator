package com.calculator.springCalculator;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Web kontroleris leidzia viduje naudoti @RequestMapping
//@RestController anotacija nurodo, jog pvz.: String tipo rezultatas turetu buti isspausdinamas klientui toks koks yra
@RestController

//Zymi konfiguracijos komponenta viduje leidzia kurti been per metodus su @Been anotacija
//Si klases lygio anotacija nurodo Spring karkasui "Atspeti" konfiguracija
//Remiantis priklausomybemis (.jar) bibliotekomis kurias programuotojas itrauke i projekta (pom.xml)
//Siuo atveju ji veikia kartu su main metodu
@EnableAutoConfiguration

public class SpringCalculatorController {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String hello(){
        //ApplicationContext yra interfeisas skirtas suteikti informacija apie aplikacijos konfiguracija
        //Siuo atveju naudojama konfiguracija paiimama is xml failo
       ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");

       //bean - klases objektas kuris atitinka singelton sablona.
       HelloWorld bean = (HelloWorld) appContext.getBean("helloWorld");
       return bean.getHello();

       // return "Spring calculator <p>" +
       //         "Galimos operacijos: <br>" +
       //         "&nbsp;&nbsp; Sudėti <br>" +
       //         "&nbsp;&nbsp; Atimti <br>" +
       //         "&nbsp;&nbsp; Dauginti <br>" +
       //         "&nbsp;&nbsp; Dalinti <br>" ;

    }



}
