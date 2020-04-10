package com.classpath.assetmgmt.dao;

import com.classpath.assetmgmt.model.Item;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public class ItemDAOImpl implements ItemDAO<Item> {

    @Override
    public Item save(Item item) {
        return null;
    }

    @Override
    public Set<Item> fetchAll() {
        return null;
    }

    @Override
    public Item update(long id, Item item) {
        return null;
    }

    @Override
    public Optional<Item> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(long id) {

    }
}