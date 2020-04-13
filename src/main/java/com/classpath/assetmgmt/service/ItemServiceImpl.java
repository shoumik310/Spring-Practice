package com.classpath.assetmgmt.service;

import com.classpath.assetmgmt.exception.ItemNotFoundException;
import com.classpath.assetmgmt.model.Item;
import com.classpath.assetmgmt.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemDAO){
        this.itemRepository = itemDAO;
    }
    @Override
    public Item saveItem(Item item) {
        return this.itemRepository.save(item);
    }

    @Override
    public Item updateItem(long itemId, Item item) {
        return this.itemRepository.save(item);
    }

    @Override
    public List<Item> fetchAllItems() {
        return this.itemRepository.findAll();
    }

    @Override
    public Item findItemById(long itemId) throws ItemNotFoundException {
        return this.itemRepository.findById(itemId)
                .orElseThrow(() ->  new ItemNotFoundException("Item with "+itemId+ " is not present"));
    }

    @Override
    public void deleteItemById(long itemId) {
        this.itemRepository.deleteById(itemId);
    }
}