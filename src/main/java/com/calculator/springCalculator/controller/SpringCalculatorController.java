package com.calculator.springCalculator.controller;

import com.calculator.springCalculator.model.Number;
import com.calculator.springCalculator.service.NumberService;
import jakarta.validation.Valid;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    //kaip perduodami duomenys skirtingiems komponentams:
    //Vartotojas -> CalculatorController -> NumberServiceImpl -> NumberDAOImpl

    @Autowired
    @Qualifier("NumberService")
    public NumberService numberService;


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
    public String calc(
            //svarbu parametras BindingResult turi eiti iskar po anotacijos @Valid
            //kitu atveju gausime klaida "Validation failed for object"
            @Valid @ModelAttribute("number") Number numb, BindingResult bindingResult,
            @RequestParam HashMap<String, String> ivedimoSarasas, ModelMap isvedimoSarasas) {

        //jeigu validacijos klaidos (zr. Number klaseje aprasyta validacija prie kiekvieno skaiciaus)
        if (bindingResult.hasErrors()) {
            //vartotojas lieka skaiciuotuvo puslapyje tol kol neistaiso validacijos klaid
            return "skaiciuotuvas";
            //vartotojas praejo validacija - skaiciuojamas rezultatas
        } else {

            int sk1 = Integer.parseInt(ivedimoSarasas.get("sk1"));
            int sk2 = Integer.parseInt(ivedimoSarasas.get("sk2"));

            double rezultatas = 0;
            String zenklas = ivedimoSarasas.get("zenklas");

            if (zenklas.equals("+")) {
                rezultatas = sk1 + sk2;

            } else if (zenklas.equals("-")) {
                rezultatas = sk1 - sk2;

            } else if (zenklas.equals("*")) {
                rezultatas = sk1 * sk2;

            } else if (zenklas.equals("/") && sk2 != 0) {
                rezultatas = (double) sk1 / sk2;
            } else {
                return "error";
            }


            //ivedimo sarasas naudojamas siusti duomenis is Sprin MVC kontrolerio i JSP faila (vaizda)
            isvedimoSarasas.put("sk1", sk1);
            isvedimoSarasas.put("sk2", sk2);
            isvedimoSarasas.put("zenklas", zenklas);
            isvedimoSarasas.put("rezultatas", rezultatas);

            //kreipiames i Service kuris savo ruostu kreipiasi i DAO ir issaugoja irasa DB
            numberService.insert(new Number(sk1, sk2, zenklas, rezultatas));

            return "skaiciuoti";
        }


        //grazinama vaizdas (forma)
        //svarbu nurodyti per application.properties prefix ir suffix
        //nes pagal tai ieskos vaizdo projekte
        // return "skaiciuoti";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String viewHome(Model model) {
        //Jeigu Model 'number' nepraeina validacijos - per ji grazinamos validacijos klaidos i View
        model.addAttribute("number", new Number());
        //graziname JSP faila kuris turi buti talpinamas "webapp -> WEB-INF -> JSP" aplanke


        return "skaiciuotuvas";

    }

    //grazinam visa sarasa
    @GetMapping("/numbers")
    public String getAllNumbers(Model model) {
        model.addAttribute("numbers", numberService.getAll());
        return "numbers";
    }

    //id - ateina is front end vartotojui pasirinkus konkretu irasa
    @GetMapping("/show{id}")
    public String getById(@RequestParam("id") int id, Model model) {
        model.addAttribute("number", numberService.getById(id));
        return "number";
    }

    //pirmaiusia istrinam poto grazinam vel visa sarasa is naujo
    @GetMapping("/delete{id}")
    public String deleteById(@RequestParam("id") int id, Model model) {
        numberService.delete(id);
        model.addAttribute("numbers", numberService.getAll());
        return "numbers";
    }

    //atnaujinant irasa pirmiausia reikia ji parodyti
    @GetMapping("/update{id}")
    public String updateById(@RequestParam("id") int id, Model model) {
        model.addAttribute("number", numberService.getById(id));
        return "update";
    }

    //kadangi forma naudoja metoda POST cia irgi nurodome POST
    @PostMapping("/updateNumber")
    public String updateNumber(@ModelAttribute("number") Number number) {
        numberService.update(number);
        //redirect nukreipia vartotoja i iraso atvaizdavimo puslapi (getById)
        return "redirect:/show?id=" + number.getId();
    }


}




