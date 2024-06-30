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
        // check for desired params of messageText
        if (message.getMessageText() != "" && message.getMessageText().length() <= 255) {
            Message newMessage = messageRepository.save(message);
            return newMessage;
        } else return null;
    }

    public List<Message> getAllMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    public Message getMessageById(int messageId) {
        // gets message or if not found returns null
        return messageRepository.findById(messageId).orElse(null);
    }

    public Message deleteMessageById(int messageId) {
        Message targetMessage = getMessageById(messageId);
        if (targetMessage != null) {
            messageRepository.deleteById(messageId);
        }
        return targetMessage;
    }

    public Message updateMessageById(int messageId, Message message) {
        // first check if desired message to update exists
        Message targetMessage = getMessageById(messageId);
        if (targetMessage != null) {
            if (message.getMessageText() != "" && message.getMessageText().length() <= 255) {
                messageRepository.save(message);
            } else return null;
        }
        // holds the message to be updated, null if does not exist
        return targetMessage;
    }

    public List<Message> getAllMessagesByAccountId(int accountId) {
        return (List<Message>) messageRepository.findAllByPostedBy(accountId);
    }

}
