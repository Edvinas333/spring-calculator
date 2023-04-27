package com.calculator.springCalculator.model;

import jakarta.validation.constraints.Min;

import javax.persistence.*;

//Entity tai POJO klase sujungta su DB esancia lentele naudojant ORM technika
@Entity
//Table susiesime POJO klase su DB esancia lentele kurios pavadinimas numbers
@Table(name = "numbers")
public class Number {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //atitinka DB autoincrement
    @Column(name = "id")
    private int id;

    @Column(name = "sk1")
    @Min(value = 0, message = "Validacijos klaida: skaičius negali būti neigiamas")
    private int sk1;

    @Column(name = "sk2")
    @Min(value = 0, message = "Validacijos klaida: skaičius negali būti neigiamas")
    private int sk2;

    @Column(name = "zenklas")
    private String zenklas;

    @Column(name = "rezultatas")
    private double rezultatas;

    //konstruktorius skirtas esamu duomenu baze irasu paieskai, redagavimui, trynimui
    public Number(int id, int sk1, int sk2, String zenklas, double rezultatas) {
        this.id = id;
        this.sk1 = sk1;
        this.sk2 = sk2;
        this.zenklas = zenklas;
        this.rezultatas = rezultatas;
    }

    //konstruktorius skirtas nauju irasu kurimui
    public Number(int sk1, int sk2, String zenklas, double rezultatas) {
        this.sk1 = sk1;
        this.sk2 = sk2;
        this.zenklas = zenklas;
        this.rezultatas = rezultatas;
    }

    //butinas tucias konstruktorius naudojant Spring framework
    public Number() {
    }

    public int getId() {
        return id;
    }

    public int getSk1() {
        return sk1;
    }

    public void setSk1(int sk1) {
        this.sk1 = sk1;
    }

    public int getSk2() {
        return sk2;
    }

    public void setSk2(int sk2) {
        this.sk2 = sk2;
    }

    public String getZenklas() {
        return zenklas;
    }

    public void setZenklas(String zenklas) {
        this.zenklas = zenklas;
    }

    public double getRezultatas() {
        return rezultatas;
    }

    public void setRezultatas(double rezultatas) {
        this.rezultatas = rezultatas;
    }

    @Override
    public String toString() {
        return "Number{" +
                "id=" + id +
                ", sk1=" + sk1 +
                ", sk2=" + sk2 +
                ", zenklas='" + zenklas + '\'' +
                ", rezultatas=" + rezultatas +
                '}';
    }
}
