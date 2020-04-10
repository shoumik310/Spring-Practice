package com.classpath.assetmgmt.service;

import com.classpath.assetmgmt.dao.ItemDAO;
import com.classpath.assetmgmt.dao.ItemDAOImpl;
import com.classpath.assetmgmt.exception.ItemNotFoundException;
import com.classpath.assetmgmt.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemDAO<Item> itemDAO;

    @Autowired
    public ItemServiceImpl(ItemDAO<Item> itemDAO){
        this.itemDAO = itemDAO;
    }
    @Override
    public Item saveItem(Item item) {
        return this.itemDAO.save(item);
    }

    @Override
    public Item updateItem(long itemId, Item item) {
        return this.itemDAO.update(itemId,item);
    }

    @Override
    public Set<Item> fetchAllItems() {
        return this.itemDAO.fetchAll();
    }

    @Override
    public Item findItemById(long itemId) throws ItemNotFoundException {
        return this.itemDAO.findById(itemId)
                .orElseThrow(() ->  new ItemNotFoundException("Item with "+itemId+ " is not present"));
    }

    @Override
    public void deleteItemById(long itemId) {
        this.itemDAO.deleteById(itemId);
    }
}