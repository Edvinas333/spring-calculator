package com.calculator.springCalculator.model;

import com.calculator.springCalculator.config.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class NumberDAOImpl implements NumberDAO{

    @Override
    public void insertEntity(Number number) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(number); //isaugo objekta i DB(obejektas = irasas lenteleje - ORM)

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Number findEntityById(int id) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();


        List<Number> numbers = entityManager
                //ORM modelyje ieskome ne DB lenteleje bet Entity (klaseje)
                .createQuery("SELECT n FROM Number n WHERE n.id = :id")
                .setParameter("id",id)
                .getResultList();//negrazinamas resultset o vietoj to galima prasyti/grazinti list

        entityManager.getTransaction().commit();
        entityManager.close();

        return numbers.get(0);
    }

    @Override
    public List<Number> findEntities() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        List<Number> numbers = entityManager
                .createQuery("SELECT n FROM Number n")
                        .getResultList();


        entityManager.getTransaction().commit();
        entityManager.close();

        return numbers;
    }


    @Override
    public void updateEntity(Number number) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        //Kad atnaujinti irasa reikia pirmiausia ji surasti DB (class = Entity, randa id ir grazina visa objekta)
        Number numberDB = entityManager.find(Number.class,number.getId());
        numberDB.setSk1(number.getSk1());
        numberDB.setSk2(number.getSk2());
        numberDB.setZenklas(number.getZenklas());
        numberDB.setRezultatas(number.getRezultatas());


        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void removeEntityById(int id) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Number numberDB = entityManager.find(Number.class,id);
        entityManager.remove(numberDB);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
