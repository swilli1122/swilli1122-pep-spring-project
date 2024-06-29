package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    Account findByUsernameAndPassword(String username, String password);

}
