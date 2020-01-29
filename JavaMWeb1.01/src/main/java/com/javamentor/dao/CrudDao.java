package com.javamentor.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {

    // Create Read Update Delete!! CRUD
    // Pattern Dao - Data accept Objects! - ОБЪЕКТ ДОСТУПА К ДАННЫМ!
    Optional<T> find(Integer id);
    void save(T model);
    void update(T model);
    void delete(String name);

    List<T> findAll();


}
