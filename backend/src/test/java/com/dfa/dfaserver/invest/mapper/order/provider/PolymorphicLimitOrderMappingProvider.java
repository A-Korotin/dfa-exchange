package com.dfa.dfaserver.invest.mapper.order.provider;

import com.dfa.dfaserver.invest.domain.order.OrderType;
import com.dfa.dfaserver.invest.dto.asset.AssetDto;
import com.dfa.dfaserver.invest.dto.order.LimitOrderDto;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class PolymorphicLimitOrderMappingProvider implements ArgumentsProvider {
    private LimitOrderDto limitOrder(OrderType type) {
        return LimitOrderDto.builder()
                .buyAsset(AssetDto.builder().chainName("TEST_BUY").ticker("TICK_BUY").build())
                .sellAsset(AssetDto.builder().chainName("TEST_SELL").ticker("TICK_SELL").build())
                .from("From_address")
                .to("To_address")
                .price(1)
                .type(type)
                .build();
    }
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(limitOrder(OrderType.BUY), limitOrder(OrderType.SELL))
        );
    }
}
