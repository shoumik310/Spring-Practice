package com.classpath.assetmgmt.dao;

import java.util.Optional;
import java.util.Set;

public interface GenericDAO<T> {
    T save(T t);

    Set<T> fetchAll();

    T update(long id, T t);

    Optional<T> findById(long id);

    void deleteById(long id);
}