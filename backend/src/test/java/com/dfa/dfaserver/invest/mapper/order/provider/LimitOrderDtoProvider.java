package com.dfa.dfaserver.invest.mapper.order.provider;

import com.dfa.dfaserver.invest.domain.order.OrderType;
import com.dfa.dfaserver.invest.dto.asset.AssetDto;
import com.dfa.dfaserver.invest.dto.order.InputLimitOrderDto;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class LimitOrderDtoProvider implements ArgumentsProvider {

    private InputLimitOrderDto limitOrder(OrderType type) {
        return InputLimitOrderDto.builder()
                .buyAsset(AssetDto.builder().chainName("TEST_BUY").ticker("TICK_BUY").build())
                .sellAsset(AssetDto.builder().chainName("TEST_SELL").ticker("TICK_SELL").build())
                .from("From_address")
                .to("To_address")
                .price(1)
                .type(type)
                .build();
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(limitOrder(OrderType.SELL)),
                Arguments.of(limitOrder(OrderType.BUY))
        );
    }
}
