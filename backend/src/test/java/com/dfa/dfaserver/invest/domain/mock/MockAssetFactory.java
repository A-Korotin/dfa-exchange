package com.dfa.dfaserver.invest.domain.mock;

import com.dfa.dfaserver.invest.domain.Asset;
import com.dfa.dfaserver.invest.domain.OISChain;
import com.dfa.dfaserver.invest.exception.factory.UnknownChainException;
import com.dfa.dfaserver.invest.factory.asset.AssetFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Primary
@Component
public class MockAssetFactory implements AssetFactory {

    @Override
    public List<Asset> loadAssetsByChain(OISChain chain) {
        if (!chain.getName().equals("TEST")) {
            throw new UnknownChainException("Unknown chain");
        }

        return List.of(new Asset("BTC", chain), new Asset("TRX", chain));
    }
}
