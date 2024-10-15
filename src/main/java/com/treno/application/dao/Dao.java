package com.treno.application.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;

public interface Dao <T> {
    T findById(long id);
    List<T> findAll();
    @Transactional
    void save(T entity);
    @Transactional
    void update(T entity);
    @Transactional
    void delete(T entity);
}
