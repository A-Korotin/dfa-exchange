package com.dfa.dfaserver.invest.service.integration;

import com.dfa.dfaserver.invest.domain.Account;
import com.dfa.dfaserver.invest.domain.Asset;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BalanceProvider {
    CompletableFuture<List<Balance>> getBalance(Account account);
    CompletableFuture<List<Balance>> getBalance(Account account, Asset asset);
}
