package com.dfa.dfaserver.domain.listeners;

import com.dfa.dfaserver.domain.Account;
import com.dfa.dfaserver.domain.Asset;
import com.dfa.dfaserver.factory.asset.AssetFactory;
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
