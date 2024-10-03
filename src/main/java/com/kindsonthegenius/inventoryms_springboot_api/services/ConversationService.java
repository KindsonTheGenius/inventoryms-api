package com.kindsonthegenius.inventoryms_springboot_api.services;

import com.kindsonthegenius.inventoryms_springboot_api.models.Conversation;
import com.kindsonthegenius.inventoryms_springboot_api.repositories.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {
    private ConversationRepository conversationRepository;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public List<Conversation> getAllConversations(){
        return conversationRepository.findAll();
    }

    public Conversation getConversationById(Long id) {
        return conversationRepository.findById(id).orElse(null);
    }

    public Conversation save(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    public void deleteConversation(Long id){
        conversationRepository.deleteById(id);
    }
}
