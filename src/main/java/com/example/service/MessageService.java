package com.example.service;

import java.util.*;
import com.example.entity.Message;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    // private AccountService accountService;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message postMessage(Message message) {
        Message newMessage = messageRepository.save(message);
        return newMessage;
    }

    public List<Message> getAllMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    public Message getMessageById(int messageId) {
        return messageRepository.findById(messageId).orElseThrow();
    }

    public int deleteMessageById(int messageId) {
        messageRepository.deleteById(messageId);
        return 0;
    }

    public int updateMessageById(int messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow();
        messageRepository.save(message);
        return 0;
    }

    public List<Message> getAllMessagesByAccountId(int accountId) {
        return messageRepository.findAllByPostedBy(accountId);
    }

}
