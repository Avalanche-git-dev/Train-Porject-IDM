package com.treno.application.dao;

import java.util.List;

public interface Dao <T> {
    T findById(int id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(T entity);
}
