package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

    Account findByUsername(String username);

    Account findByAccountId(int accountId);

    Account findByUsernameAndPassword(String username, String password);

}
