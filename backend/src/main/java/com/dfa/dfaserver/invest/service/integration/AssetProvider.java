package com.dfa.dfaserver.invest.service.integration;

import com.dfa.dfaserver.invest.domain.Asset;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AssetProvider {
    CompletableFuture<List<Asset>> getAssets();
}
