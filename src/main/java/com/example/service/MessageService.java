package com.example.service;

import java.util.*;
import com.example.entity.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

@Service
public class MessageService {

    private List<Message> messageList = new ArrayList<>();
    private AccountService accountService;

    @Autowired
    public MessageService(AccountService accountService) {
        this.accountService = accountService;
    }

    public Message postMessage(Message message) {
        return null;
    }

    public List<Message> getAllMessages() {
        return null;
    }

    public Message getMessageById(int messageId) {
        return null;
    }

    public int deleteMessageById(int messageId) {
        return 0;
    }

    public int updateMessageById(Message message) {
        return 0;
    }

    public List<Message> getAllMessagesByAccountId(int accountId) {
        return null;
    }

}
