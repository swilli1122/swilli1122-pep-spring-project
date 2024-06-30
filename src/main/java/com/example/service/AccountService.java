package com.example.service;

import java.util.*;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private MessageService messageService;

    @Autowired
    public AccountService(MessageService messageService, AccountRepository accountRepository) {
        this.messageService = messageService;
        this.accountRepository = accountRepository;
    }

    public Account newAccount(Account newAccount) {
        Account account = accountRepository.save(newAccount);
        return account;
    }

    public Account login(Account account) {
        return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
    }

    // added method to check if an account already exists by given username, will return a null Account if not
    public Account accountExistsByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    // added method to check if account already exists by given accountId
    public Account accountExistsById(int accountId) {
        return accountRepository.findByAccountId(accountId);
    }

}
