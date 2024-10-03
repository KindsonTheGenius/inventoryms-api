package com.kindsonthegenius.inventoryms_springboot_api.controllers;


import com.kindsonthegenius.inventoryms_springboot_api.models.Message;
import com.kindsonthegenius.inventoryms_springboot_api.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {


    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    public List<Message> getMessagees(){
        return messageService.getAllMessages();
    }

    @GetMapping("/message/{id}")
    public Message getMessage(@PathVariable("id") Long id){
        return messageService.getMessageById(id);
    }

    @PutMapping("/message/{id}")
    public Message updateMessage(@RequestBody() Message message, @PathVariable("id") Long id){
        return messageService.save(message);
    }

    @PostMapping("/messages")
    public Message addNew(@RequestBody() Message message){
        return messageService.save(message);
    }

    @DeleteMapping("/message/{id}")
    public void deleteMessage(@PathVariable("id") Long id){
        messageService.deleteMessage(id);
    }
}
