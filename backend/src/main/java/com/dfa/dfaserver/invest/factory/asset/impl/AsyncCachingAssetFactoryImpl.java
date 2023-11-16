package com.dfa.dfaserver.invest.factory.asset.impl;

import com.dfa.dfaserver.invest.domain.Asset;
import com.dfa.dfaserver.invest.domain.OISChain;
import com.dfa.dfaserver.invest.exception.factory.UnknownChainException;
import com.dfa.dfaserver.invest.factory.asset.AssetFactory;
import com.dfa.dfaserver.invest.service.integration.AssetProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Primary
@Component
@RequiredArgsConstructor
public class AsyncCachingAssetFactoryImpl implements AssetFactory {
    private final ApplicationContext context;

    @Override
    @Cacheable("assets")
    public List<Asset> loadAssetsByChain(OISChain chain) {
        AssetProvider provider;
        try {
            provider = context.getBean(chain.getName(), AssetProvider.class);
        } catch (NoSuchBeanDefinitionException e) {
            throw new UnknownChainException(chain.getName());
        }

        return provider.getAssets().join();
    }
}
