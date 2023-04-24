package com.calculator.springCalculator;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

//Web kontroleris leidzia viduje naudoti @RequestMapping
//@RestController anotacija nurodo, jog pvz.: String tipo rezultatas turetu buti isspausdinamas klientui toks koks yra

//@RestController

//@RestController anotacija naudojama tada kai front end nenaudojam springo (js, react, angular)
//dazniausiai grazinami formatai (json, xml)
//tai yra negraziname vaizdo (formos, html, JSP)

//kadangi mums reikia grazinti vaizda (view) pagal spring mvc naudosime anotacija @Controller
@Controller


//Zymi konfiguracijos komponenta viduje leidzia kurti been per metodus su @Been anotacija
//Si klases lygio anotacija nurodo Spring karkasui "Atspeti" konfiguracija
//Remiantis priklausomybemis (.jar) bibliotekomis kurias programuotojas itrauke i projekta (pom.xml)
//Siuo atveju ji veikia kartu su main metodu
@EnableAutoConfiguration

public class SpringCalculatorController {
//    @RequestMapping(method = RequestMethod.GET, value = "/")
//    public String hello() {
//        //ApplicationContext yra interfeisas skirtas suteikti informacija apie aplikacijos konfiguracija
//        //Siuo atveju naudojama konfiguracija paiimama is xml failo
//        ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
//
//        //bean - klases objektas kuris atitinka singelton sablona.
//        HelloWorld bean = (HelloWorld) appContext.getBean("helloWorld");
//        return bean.getHello();
//
//        // return "Spring calculator <p>" +
//        //         "Galimos operacijos: <br>" +
//        //         "&nbsp;&nbsp; Sudėti <br>" +
//        //         "&nbsp;&nbsp; Atimti <br>" +
//        //         "&nbsp;&nbsp; Dauginti <br>" +
//        //         "&nbsp;&nbsp; Dalinti <br>" ;
//
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/calc")
//    //@RequestParam anotacija perduoda siuo atveju per URL duomenis du skaiciai, operacijos zenklas kurie patalpinami i sarasa (raktas, reiksme)
//    //pirmas String yra raktas (sk1,sk2,zenklas) antras String reiksme (8, 5, +)
//    //Raktai tiek front end tiek back end turi sutapti!
//    public String calc(@RequestParam HashMap<String, String> skaiciai) {
//        //per URL perduodamas raktas turi pavadinima sk1
//        //pagal rakta sk1 istraukiama reiksme pvz tarkim ivede 8
//        //reikia konvertuoti is String i int kad paskaiciuoti rezultata
//        // pvz: http://localhost:8080/calc?sk1=2&sk2=3&zenklas=%2B
//        // decoder: https://meyerweb.com/eric/tools/dencoder/
//        int sk1 = Integer.parseInt(skaiciai.get("sk1"));
//        int sk2 = Integer.parseInt(skaiciai.get("sk2"));
//        double rezultatas = 0;
//        //TODO:Suskaiciuoti ir aspausdinti rezultata nurodant kas is ko buvo gauta
//        String zenklas = skaiciai.get("zenklas");
//
//        if (zenklas.equals("+")) {
//            rezultatas = sk1 + sk2;
//
//        } else if (zenklas.equals("-")) {
//            rezultatas = sk1 - sk2;
//
//        }else if (zenklas.equals("*")){
//            rezultatas = sk1 * sk2;
//
//        }else if (zenklas.equals("/") && sk2 != 0){
//            rezultatas = sk1 / sk2;
//        }
//
//
//        return sk1 + " " + zenklas + " " + sk2 + " " + "= " + rezultatas;
//
//    }

    //kadangi skaiciuotuvo forma naudoja post metoda cia irgi nurodysime POST
    @RequestMapping(method = RequestMethod.POST, value = "/calc")
    public String calc(@RequestParam HashMap<String, String> ivedimoSarasas, ModelMap isvedimoSarasas) {
        int sk1 = Integer.parseInt(ivedimoSarasas.get("sk1"));
        int sk2 = Integer.parseInt(ivedimoSarasas.get("sk2"));

        double rezultatas = 0;
        String zenklas = ivedimoSarasas.get("zenklas");

        if (zenklas.equals("+")) {
            rezultatas = sk1 + sk2;

        } else if (zenklas.equals("-")) {
            rezultatas = sk1 - sk2;

        }else if (zenklas.equals("*")){
            rezultatas = sk1 * sk2;

        }else if (zenklas.equals("/") && sk2 != 0){
            rezultatas = sk1 / sk2;
        }

        //ivedimo sarasas naudojamas siusti duomenis is Sprin MVC kontrolerio i JSP faila (vaizda)
        isvedimoSarasas.put("sk1",sk1);
        isvedimoSarasas.put("sk2",sk2);
        isvedimoSarasas.put("zenklas",zenklas);
        isvedimoSarasas.put("rezultatas",rezultatas);

        //grazinama vaizdas (forma)
        //svarbu nurodyti per application.properties prefix ir suffix
        //nes pagal tai ieskos vaizdo projekte
        return "skaiciuoti";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
public String viewHome() {
        //graziname JSP faila kuris turi buti talpinamas "webapp -> WEB-INF -> JSP" aplanke


    return "skaiciuotuvas";

    }


}




