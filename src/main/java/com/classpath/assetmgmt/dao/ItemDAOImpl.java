package com.classpath.assetmgmt.dao;

import com.classpath.assetmgmt.model.Item;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class ItemDAOImpl implements ItemDAO<Item> {

    private static Set<Item> items = new HashSet<>(
            Arrays.asList(
                    Item.builder().name("book").price(550).build(),
                    Item.builder().name("pen").price(45).build(),
                    Item.builder().name("keyboard").price(45).build()
                    ));



    @Override
    public Item save(Item item) {
        return null;
    }

    @Override
    public Set<Item> fetchAll() {
        return items;
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