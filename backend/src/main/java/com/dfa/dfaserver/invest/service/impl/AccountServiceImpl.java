package com.dfa.dfaserver.invest.service.impl;

import com.dfa.dfaserver.invest.domain.Account;
import com.dfa.dfaserver.invest.repository.AccountRepository;
import com.dfa.dfaserver.invest.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Optional<Account> findById(String s) {
        return accountRepository.findById(s);
    }

    @Override
    public boolean existsById(String s) {
        return accountRepository.existsById(s);
    }

    @Override
    public Account save(Account entity) {
        return accountRepository.save(entity);
    }

    @Override
    public Account updateById(String s, Account entity) {
        entity.setAddress(s);
        return save(entity);
    }

    @Override
    public void deleteById(String s) {
        accountRepository.deleteById(s);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}
