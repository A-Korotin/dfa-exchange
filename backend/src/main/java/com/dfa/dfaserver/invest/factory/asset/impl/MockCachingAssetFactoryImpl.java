package com.dfa.dfaserver.invest.factory.asset.impl;

import com.dfa.dfaserver.invest.domain.Asset;
import com.dfa.dfaserver.invest.domain.OISChain;
import com.dfa.dfaserver.invest.exception.factory.UnknownChainException;
import com.dfa.dfaserver.invest.factory.asset.AssetFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class MockCachingAssetFactoryImpl implements AssetFactory {

    private final Map<OISChain, List<Asset>> assetCache = new HashMap<>();
    {
        OISChain chain = new OISChain("Test_chain");
        assetCache.put(chain, List.of(new Asset("TRX", chain), new Asset("BTC", chain)));
    }

    @Override
    public List<Asset> loadAssetsByChain(OISChain chain) {
        List<Asset> assets = assetCache.get(chain);

        if (assets == null) {
            throw new UnknownChainException("Chain '%s' is not a valid Blockchain".formatted(chain.getName()));
        }

        return assets;
    }
}
