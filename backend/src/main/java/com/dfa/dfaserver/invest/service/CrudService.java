package com.dfa.dfaserver.invest.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {
    Optional<T> findById(ID id);
    boolean existsById(ID id);
    T save(T entity);
    T updateById(ID id, T entity);
    void deleteById(ID id);
    List<T> findAll();
}
