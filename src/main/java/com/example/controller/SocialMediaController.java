package com.example.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Message;
import com.example.entity.Account;
import com.example.service.MessageService;
import com.example.service.AccountService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    private MessageService messageService;
    private AccountService accountService;

    @Autowired
    public SocialMediaController(MessageService messageService, AccountService accountService) {
        this.messageService = messageService;
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> newAccount(@RequestBody Account account) {
        Account newAccount = accountService.newAccount(account);
        // add getAccountByUsername check here
        if (newAccount != null) {
            return ResponseEntity.status(200).body(newAccount);
        } else {
            return ResponseEntity.status(401).body(newAccount);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        Account loginAccount = accountService.login(account);
        if (loginAccount != null) {
            return ResponseEntity.status(200).body(loginAccount);
        } else {
            return ResponseEntity.status(401).body(loginAccount);
        }
        
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message) {
        Message newMessage = messageService.postMessage(message);
        if (newMessage != null) {
            return ResponseEntity.status(200).body(newMessage);
        } else {
            return ResponseEntity.status(400).body(newMessage);
        }
    }

    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable int messageId) {
        Message message = messageService.getMessageById(messageId)
        return ResponseEntity.status(200).body(message);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int messageId) {
        int rowsUpdated = messageService.deleteMessageById(messageId);
        return ResponseEntity.status(200).body(rowsUpdated);
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessageById(@RequestBody Message message) {
        int rowsUpdated = messageService.updateMessageById(message);
        if (rowsUpdated == 1) {
            return ResponseEntity.status(200).body(rowsUpdated);
        } else {
            return ResponseEntity.status(400).body(rowsUpdated);
        }
    }

    @GetMapping("/accounts/{accountId}/messages")
    public List<Message> getAllMessagesByAccountId(int accountId) {
        return messageService.getAllMessagesByAccountId(accountId);
    }

}
