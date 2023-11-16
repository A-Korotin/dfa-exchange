package com.dfa.dfaserver.invest.service.integration;

import com.dfa.dfaserver.invest.domain.Account;
import com.dfa.dfaserver.invest.domain.Asset;
import com.dfa.dfaserver.invest.domain.transaction.Transaction;

import java.util.concurrent.CompletableFuture;

public interface TransactionExecutor {
    CompletableFuture<Transaction> sendTransfer(Account from, Account to, Asset asset, int amount);
}
