package com.dfa.dfaserver.factory.asset;

import com.dfa.dfaserver.domain.Asset;
import com.dfa.dfaserver.domain.OISChain;

import java.util.List;

public interface AssetFactory {
    List<Asset> loadAssetsByChain(OISChain chain);
}
