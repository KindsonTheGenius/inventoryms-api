package com.kindsonthegenius.inventoryms_springboot_api.controllers;


import com.kindsonthegenius.inventoryms_springboot_api.models.Conversation;
import com.kindsonthegenius.inventoryms_springboot_api.services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConversationController {

    private ConversationService conversationService;

    @Autowired
    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping("/conversations")
    public List<Conversation> getConversationes(){
        return conversationService.getAllConversations();
    }

    @GetMapping("/conversation/{id}")
    public Conversation getConversation(@PathVariable("id") Long id){
        return conversationService.getConversationById(id);
    }

    @PutMapping("/conversation/{id}")
    public Conversation updateConversation(@RequestBody() Conversation conversation, @PathVariable("id") Long id){
        return conversationService.save(conversation);
    }

    @PostMapping("/conversations")
    public Conversation addNew(@RequestBody() Conversation conversation){
        return conversationService.save(conversation);
    }

    @DeleteMapping("/conversation/{id}")
    public void deleteConversation(@PathVariable("id") Long id){
        conversationService.deleteConversation(id);
    }
}
