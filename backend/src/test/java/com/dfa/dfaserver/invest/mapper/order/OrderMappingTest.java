package com.dfa.dfaserver.invest.mapper.order;

import com.dfa.dfaserver.invest.domain.mock.MockOrderExecutorService;
import com.dfa.dfaserver.invest.domain.order.LimitOrder;
import com.dfa.dfaserver.invest.domain.order.MarketOrder;
import com.dfa.dfaserver.invest.dto.order.LimitOrderDto;
import com.dfa.dfaserver.invest.dto.order.MarketOrderDto;
import com.dfa.dfaserver.invest.mapper.order.provider.LimitOrderDtoProvider;
import com.dfa.dfaserver.invest.mapper.order.provider.MarketOrderDtoProvider;
import com.dfa.dfaserver.invest.mapper.order.provider.PolymorphicLimitOrderMappingProvider;
import com.dfa.dfaserver.invest.mapper.order.provider.PolymorphicMarketOrderMappingProvider;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderMappingTest {
    @Autowired
    private LimitOrderMapper limitOrderMapper;

    @Autowired
    private MarketOrderMapper marketOrderMapper;

    @Autowired
    private MockOrderExecutorService orderExecutorService;

    @Order(1)
    @ParameterizedTest
    @DisplayName("Test mapping LimitOrderDto -> *LimitOrder and vice versa")
    @ArgumentsSource(LimitOrderDtoProvider.class)
    public void limitOrderToDtoMappingTest(LimitOrderDto dto) {
        LimitOrder limitOrder = limitOrderMapper.fromDto(dto);

        assertEquals(limitOrder.getFrom().getAddress(), dto.from);
        assertEquals(limitOrder.getTo().getAddress(), dto.to);
        assertEquals(limitOrder.getPrice(), dto.price);
        assertEquals(limitOrder.getBuyAsset().getChain().getName(), dto.buyAsset.chainName);
        assertEquals(limitOrder.getBuyAsset().getTicker(), dto.buyAsset.ticker);
        assertEquals(limitOrder.getSellAsset().getChain().getName(), dto.sellAsset.chainName);
        assertEquals(limitOrder.getSellAsset().getTicker(), dto.sellAsset.ticker);

        LimitOrderDto mappedDto = limitOrderMapper.toDto(limitOrder);

        assertEquals(mappedDto.from, limitOrder.getFrom().getAddress());
        assertEquals(mappedDto.to, limitOrder.getTo().getAddress());
        assertEquals(mappedDto.price, limitOrder.getPrice());
        assertEquals(mappedDto.buyAsset.ticker, limitOrder.getBuyAsset().getTicker());
        assertEquals(mappedDto.buyAsset.chainName, limitOrder.getBuyAsset().getChain().getName());
        assertEquals(mappedDto.sellAsset.ticker, limitOrder.getSellAsset().getTicker());
        assertEquals(mappedDto.sellAsset.chainName, limitOrder.getSellAsset().getChain().getName());
    }

    @Order(2)
    @ParameterizedTest
    @DisplayName("Test mapping MarketOrderDto -> *MarketOrder and vice versa")
    @ArgumentsSource(MarketOrderDtoProvider.class)
    public void marketOrderToDtoMappingTest(MarketOrderDto dto) {
        MarketOrder marketOrder = marketOrderMapper.fromDto(dto);

        assertEquals(marketOrder.getFrom().getAddress(), dto.from);
        assertEquals(marketOrder.getTo().getAddress(), dto.to);
        assertEquals(marketOrder.getBuyAsset().getChain().getName(), dto.buyAsset.chainName);
        assertEquals(marketOrder.getBuyAsset().getTicker(), dto.buyAsset.ticker);
        assertEquals(marketOrder.getSellAsset().getChain().getName(), dto.sellAsset.chainName);
        assertEquals(marketOrder.getSellAsset().getTicker(), dto.sellAsset.ticker);

        MarketOrderDto mappedDto = marketOrderMapper.toDto(marketOrder);

        assertEquals(mappedDto.from, marketOrder.getFrom().getAddress());
        assertEquals(mappedDto.to, marketOrder.getTo().getAddress());
        assertEquals(mappedDto.buyAsset.ticker, marketOrder.getBuyAsset().getTicker());
        assertEquals(mappedDto.buyAsset.chainName, marketOrder.getBuyAsset().getChain().getName());
        assertEquals(mappedDto.sellAsset.ticker, marketOrder.getSellAsset().getTicker());
        assertEquals(mappedDto.sellAsset.chainName, marketOrder.getSellAsset().getChain().getName());
    }

    @Order(3)
    @ParameterizedTest
    @DisplayName("Polymorphic limit order mapping test")
    @ArgumentsSource(PolymorphicLimitOrderMappingProvider.class)
    public void polymorphicMappingTest(LimitOrderDto o1, LimitOrderDto o2) {
        limitOrderMapper.fromDto(o1).execute(orderExecutorService);
        limitOrderMapper.fromDto(o2).execute(orderExecutorService);
        assertEquals(1, orderExecutorService.getBuyLimitExecutions());
        assertEquals(1, orderExecutorService.getSellLimitExecutions());
    }

    @Order(4)
    @ParameterizedTest
    @DisplayName("Polymorphic market order mapping test")
    @ArgumentsSource(PolymorphicMarketOrderMappingProvider.class)
    public void polymorphicMappingTest(MarketOrderDto o1, MarketOrderDto o2) {
        marketOrderMapper.fromDto(o1).execute(orderExecutorService);
        marketOrderMapper.fromDto(o2).execute(orderExecutorService);
        assertEquals(1, orderExecutorService.getBuyMarketExecutions());
        assertEquals(1, orderExecutorService.getSellMarketExecutions());
    }
}
