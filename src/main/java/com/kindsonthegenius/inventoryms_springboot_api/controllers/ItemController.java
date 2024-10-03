package com.kindsonthegenius.inventoryms_springboot_api.controllers;

import com.kindsonthegenius.inventoryms_springboot_api.models.Item;
import com.kindsonthegenius.inventoryms_springboot_api.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {


    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public List<Item> getItemes(){
        return itemService.getAllItems();
    }

    @GetMapping("/item/{id}")
    public Item getItem(@PathVariable("id") Long id){
        return itemService.getItemById(id);
    }

    @PutMapping("/item/{id}")
    public Item updateItem(@RequestBody() Item item, @PathVariable("id") Long id){
        return itemService.save(item);
    }

    @PostMapping("/items")
    public Item addNew(@RequestBody() Item item){
        return itemService.save(item);
    }

    @DeleteMapping("/item/{id}")
    public void deleteItem(@PathVariable("id") Long id){
        itemService.deleteItem(id);
    }
}
