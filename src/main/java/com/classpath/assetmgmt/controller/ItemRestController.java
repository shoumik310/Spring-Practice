package com.classpath.assetmgmt.controller;

import com.classpath.assetmgmt.exception.ItemNotFoundException;
import com.classpath.assetmgmt.model.Item;
import com.classpath.assetmgmt.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/items")
public class ItemRestController {

    private ItemService itemService;

    @Autowired
    public ItemRestController(ItemService itemService){
        this.itemService = itemService;
    }

    @PostMapping()
    public Item acceptItem (Item item){
        return this.itemService.saveItem(item);
    }

    @GetMapping()
    public Set<Item> findAll(){
        return this.itemService.fetchAllItems();
    }

    @GetMapping("/{id}")
    public Item fetchItemById(@PathVariable long itemId) {
        try {
            return this.itemService.findItemById(itemId);
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/{itemId}")
    public Item updateItem(long itemId, Item item){
        return this.itemService.updateItem(itemId, item);
    }


    @DeleteMapping("/{itemId}")
    public void deleteItem(long itemId){
        this.itemService.deleteItemById(itemId);
    }
}