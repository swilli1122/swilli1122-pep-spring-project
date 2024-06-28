package com.example.service;

import java.util.*;
import com.example.entity.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

@Service
public class AccountService {

    private List<Account> accountList = new ArrayList<>();
    private MessageService messageService;

    @Autowired
    public AccountService(MessageService messageService) {
        this.messageService = messageService;
    }

    public Account newAccount(Account newAccount) {
        return null;
    }

    public Account login(Account account) {
        return null;
    }

}
