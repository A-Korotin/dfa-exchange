package com.dfa.dfaserver.invest.mapper.order.provider;

import com.dfa.dfaserver.invest.domain.order.OrderType;
import com.dfa.dfaserver.invest.dto.asset.AssetDto;
import com.dfa.dfaserver.invest.dto.order.MarketOrderDto;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class PolymorphicMarketOrderMappingProvider implements ArgumentsProvider {
    private MarketOrderDto marketOrder(OrderType type) {
        return MarketOrderDto.builder()
                .buyAsset(AssetDto.builder().chainName("TEST_BUY").ticker("TICK_BUY").build())
                .sellAsset(AssetDto.builder().chainName("TEST_SELL").ticker("TICK_SELL").build())
                .from("From_address")
                .to("To_address")
                .type(type)
                .build();
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(marketOrder(OrderType.SELL), marketOrder(OrderType.BUY))
        );
    }
}
