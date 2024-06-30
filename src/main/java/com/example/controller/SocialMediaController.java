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
        // check if account already exists
        if (accountService.accountExistsByUsername(account.getUsername()) != null) {
            return ResponseEntity.status(409).body(account);
        }
        // save the account to data
        Account newAccount = accountService.newAccount(account);
        if (newAccount != null) {
            // successful save
            return ResponseEntity.status(200).body(newAccount);
        } else {
            //something went wrong
            return ResponseEntity.status(401).body(newAccount);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        // creates an Account with username and password to check
        Account loginAccount = accountService.login(account);
        if (loginAccount != null) {
            // found an Account with matching username and password
            return ResponseEntity.status(200).body(loginAccount);
        } else {
            // no Account found
            return ResponseEntity.status(401).body(loginAccount);
        }
        
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message) {
        // check if the account trying to post message exists 
        if (accountService.accountExistsById(message.getPostedBy()) == null) {
            return ResponseEntity.status(400).body(message);
        }
        // creates the message and saves in db
        Message newMessage = messageService.postMessage(message);
        if (newMessage != null) {
            // new message successfully saved
            return ResponseEntity.status(200).body(newMessage);
        } else {
            // error in message content
            return ResponseEntity.status(400).body(newMessage);
        }
    }

    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable int messageId) {
        Message message = messageService.getMessageById(messageId);
        return ResponseEntity.status(200).body(message);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int messageId) {
        Message targetMessage = messageService.deleteMessageById(messageId);
        if (targetMessage != null) {
            // target message found and successfully deleted
            return ResponseEntity.status(200).body(1);
        } else {
            // target message was not found
            return ResponseEntity.status(200).body(null);
        }
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessageById(@PathVariable int messageId, @RequestBody Message message) {
        // passes target of message update and update information
        Message targetMessage = messageService.updateMessageById(messageId, message);
        if (targetMessage != null) {
            // target message successfully updated
            return ResponseEntity.status(200).body(1);
        } else {
            // target message not found OR invalid message text 
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/accounts/{accountId}/messages")
    public List<Message> getAllMessagesByAccountId(@PathVariable int accountId) {
        return messageService.getAllMessagesByAccountId(accountId);
    }

}
