package com.pocketplanner.service;

import com.pocketplanner.model.Account;
import com.pocketplanner.model.User;
import com.pocketplanner.model.dto.AccountCreateDto;
import com.pocketplanner.repository.AccountRepository;
import com.pocketplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public List<Account> getAccountsByUserId(Long userId) {
        return accountRepository.findByUserId(userId);
    }

    public Boolean createAccount(Long userId, AccountCreateDto accountCreateDto) {
        Account account = new Account();
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            account.setUser(user.get());
        }
        account.setName(accountCreateDto.getName());
        account.setBalance(accountCreateDto.getBalance());
        Account createdAccount = accountRepository.save(account);
        return getAccountById(createdAccount.getId()).isPresent();
    }

    public Boolean deleteAccount(Long id) {
        Optional<Account> accountCheck = getAccountById(id);
        if (accountCheck.isEmpty()) {
            return false;
        }
        accountRepository.deleteById(id);
        return getAccountById(id).isEmpty();
    }
}
