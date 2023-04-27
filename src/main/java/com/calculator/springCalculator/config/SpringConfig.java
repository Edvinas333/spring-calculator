package com.calculator.springCalculator.config;

import com.calculator.springCalculator.model.NumberDAO;
import com.calculator.springCalculator.model.NumberDAOImpl;
import com.calculator.springCalculator.service.NumberService;
import com.calculator.springCalculator.service.NumberServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration anotacija zyminti konfiguracijos komponenta
//viduje leidzia kurti Bean per metodus su @Bean anotacija
@Configuration
public class SpringConfig {

    //@Bean tai objektai kurie sudaro Spring aplikacijos pagrinda
    //Paprastai tai JAVA klase realizuojanti tam tikra INTERFACE ir JAVA @Bean specifikacija
    //@Bean atitinka Singelton sablona - programines irangos projektavimo schema
    //kuri riboja klases ivykdyma vienu vieninteliu egzemplioriumi
    //Tai naudinga kai reikia tiksliai vieno objekto norint kordinuoti veiksmus visoje sistemoje
    @Bean
    //@Qualifier anotacija kartu su @Autowired patikslina su kuriuo konkreciai bean susieti priklausomybe
    //jeigu @Configuration klaseje yra daugiau negu vienas bean, @Qualifier anotacija yra privaloma
    //kitu atveju metama klaida:
    //'Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed'
    @Qualifier("NumberDAO")
    public NumberDAO getNumberDAO(){
        return new NumberDAOImpl();
    }

    @Bean
    @Qualifier("NumberService")
    public NumberService getNumberService(){
        return new NumberServiceImpl();
    }

}
