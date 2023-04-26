package com.calculator.springCalculator.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//klase skirta entity manager factory
//JPAUtil yra pagalbinė klasė, skirta padėti inicializuoti ir konfigūruoti Java Persistence API (JPA) objektų valdymo kontekstą.
// Ši klasė gali būti naudojama pradiniame programos paleidime, kad būtų sukurtas ir sukonfigūruotas EntityManagerFactory objektas.
public class JPAUtil {

    public static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
    private static EntityManagerFactory entityManagerFactory;

    //entitymanagerfactory per aplikacijos veikima bus tik viena(tuo tarpu sesiju gali buti daug)
    //entitymanagerfactory veikia SINGLETON sablonu(pattern)
    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return entityManagerFactory;
    }
    //uzdaro fabrika
    public static void shutdown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

}
