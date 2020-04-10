package com.classpath.assetmgmt.service;

import com.classpath.assetmgmt.exception.ItemNotFoundException;
import com.classpath.assetmgmt.model.Item;

import java.util.Set;

public interface ItemService {

    Item saveItem(Item item);

    Item updateItem(long itemId, Item item);

    Set<Item> fetchAllItems();

    Item findItemById(long itemId) throws ItemNotFoundException;

    void deleteItemById(long itemId);

}