package com.dfa.dfaserver.invest.factory.asset;

import com.dfa.dfaserver.invest.domain.Asset;
import com.dfa.dfaserver.invest.domain.OISChain;

import java.util.List;

public interface AssetFactory {
    List<Asset> loadAssetsByChain(OISChain chain);
}
