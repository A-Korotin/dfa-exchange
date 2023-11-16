package com.dfa.dfaserver.invest.domain.listener;

import com.dfa.dfaserver.invest.domain.Account;
import com.dfa.dfaserver.invest.domain.Asset;
import com.dfa.dfaserver.invest.factory.asset.AssetFactory;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountInitializerListener {
    private AssetFactory assetFactory;

    @Autowired // entity listener specific
    public void setAssetFactory(AssetFactory assetFactory) {
        this.assetFactory = assetFactory;
    }

    @PostLoad
    @PrePersist
    @PreUpdate
    public void postAccountLoad(Account account) {
        List<Asset> assets = assetFactory.loadAssetsByChain(account.getChain());
        account.setAssets(assets);
    }
}
