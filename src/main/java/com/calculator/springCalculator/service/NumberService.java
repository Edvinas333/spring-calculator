package com.calculator.springCalculator.service;

import com.calculator.springCalculator.model.Number;

import java.util.List;

public interface NumberService {

    void insert(Number number);
    Number getById(int id);
    List<Number> getAll();
    void update(Number number);
    void delete(int id);

}
