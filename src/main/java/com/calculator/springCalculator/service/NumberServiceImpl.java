package com.calculator.springCalculator.service;

import com.calculator.springCalculator.model.Number;
import com.calculator.springCalculator.model.NumberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service - sluoksnis skirtas "verslo" logikai CRUD
//po service sluoksniu kreipemes i DAO/DB
@Service
public class NumberServiceImpl implements NumberService {

    //Autowired naudojama automatineo priklausomybiu injekcijai (IOC - Inversion of control)
    //kad panaudoti @Autowired anotacija reikia pirmiausia tureti apsirasius @Bean @Configuration klase
    @Autowired

    //@Qualifier anotacija kartu su @Autowired patikslina su kuriuo konkreciai bean susieti priklausomybe
    //jeigu @Configuration klaseje yra daugiau negu vienas bean, @Qualifier anotacija yra privaloma
    //kitu atveju metama klaida:
    //'Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed'
    @Qualifier("NumberDAO")
    private NumberDAO numberDAO;

    @Override
    public void insert(Number number) {
    numberDAO.insertEntity(number);
    }

    @Override
    public Number getById(int id) {

        return numberDAO.findEntityById(id);
    }

    @Override
    public List<Number> getAll() {
        return numberDAO.findEntities();
    }

    @Override
    public void update(Number number) {
    numberDAO.updateEntity(number);
    }

    @Override
    public void delete(int id) {
    numberDAO.removeEntityById(id);
    }
}
