package com.dfa.dfaserver.invest.service.integration.impl;

import com.dfa.dfaserver.invest.domain.Account;
import com.dfa.dfaserver.invest.domain.Asset;
import com.dfa.dfaserver.invest.domain.OISChain;
import com.dfa.dfaserver.invest.domain.transaction.Transaction;
import com.dfa.dfaserver.invest.service.integration.Balance;
import com.dfa.dfaserver.invest.service.integration.OISProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service("TEST")
@RequiredArgsConstructor
public class MockOISProvider implements OISProvider {

    private List<Asset> mockAssetHttpQuery() {
        try {
            Thread.sleep(900); // emulate delay
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        OISChain chain = new OISChain("TEST");
        return  List.of(new Asset("BTC", chain), new Asset("TRX", chain));
    }

    @Override
    @Async
    public CompletableFuture<List<Asset>> getAssets() {
        return CompletableFuture.supplyAsync(this::mockAssetHttpQuery);
    }

    @Override
    @Async
    public CompletableFuture<List<Balance>> getBalance(Account account) {
        return null;
    }

    @Override
    @Async
    public CompletableFuture<List<Balance>> getBalance(Account account, Asset asset) {
        return null;
    }

    @Override
    @Async
    public CompletableFuture<Transaction> sendTransfer(Account from, Account to, Asset asset, int amount) {
        return null;
    }
}
