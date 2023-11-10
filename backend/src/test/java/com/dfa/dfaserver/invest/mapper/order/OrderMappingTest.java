package com.dfa.dfaserver.invest.mapper.order;

import com.dfa.dfaserver.invest.domain.mock.MockOrderExecutorService;
import com.dfa.dfaserver.invest.domain.order.LimitOrder;
import com.dfa.dfaserver.invest.domain.order.MarketOrder;
import com.dfa.dfaserver.invest.dto.order.InputLimitOrderDto;
import com.dfa.dfaserver.invest.dto.order.InputMarketOrderDto;
import com.dfa.dfaserver.invest.dto.order.LimitOrderDto;
import com.dfa.dfaserver.invest.dto.order.MarketOrderDto;
import com.dfa.dfaserver.invest.mapper.order.provider.LimitOrderDtoProvider;
import com.dfa.dfaserver.invest.mapper.order.provider.MarketOrderDtoProvider;
import com.dfa.dfaserver.invest.mapper.order.provider.PolymorphicLimitOrderMappingProvider;
import com.dfa.dfaserver.invest.mapper.order.provider.PolymorphicMarketOrderMappingProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OrderMappingTest {
    @Autowired
    private LimitOrderMapper limitOrderMapper;

    @Autowired
    private MarketOrderMapper marketOrderMapper;

    @Autowired
    private MockOrderExecutorService orderExecutorService;

    @ParameterizedTest
    @DisplayName("Test mapping LimitOrderDto -> *LimitOrder and vice versa")
    @ArgumentsSource(LimitOrderDtoProvider.class)
    public void limitOrderToDtoMappingTest(InputLimitOrderDto dto) {
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

    @ParameterizedTest
    @DisplayName("Test mapping MarketOrderDto -> *MarketOrder and vice versa")
    @ArgumentsSource(MarketOrderDtoProvider.class)
    public void marketOrderToDtoMappingTest(InputMarketOrderDto dto) {
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

    @ParameterizedTest
    @DisplayName("Polymorphic limit order mapping test")
    @ArgumentsSource(PolymorphicLimitOrderMappingProvider.class)
    public void polymorphicMappingTest(InputLimitOrderDto o1, InputLimitOrderDto o2) {
        limitOrderMapper.fromDto(o1).execute(orderExecutorService);
        limitOrderMapper.fromDto(o2).execute(orderExecutorService);
        assertEquals(1, orderExecutorService.getBuyLimitExecutions());
        assertEquals(1, orderExecutorService.getSellLimitExecutions());
    }

    @ParameterizedTest
    @DisplayName("Polymorphic market order mapping test")
    @ArgumentsSource(PolymorphicMarketOrderMappingProvider.class)
    public void polymorphicMappingTest(InputMarketOrderDto o1, InputMarketOrderDto o2) {
        marketOrderMapper.fromDto(o1).execute(orderExecutorService);
        marketOrderMapper.fromDto(o2).execute(orderExecutorService);
        assertEquals(1, orderExecutorService.getBuyMarketExecutions());
        assertEquals(1, orderExecutorService.getSellMarketExecutions());
    }
}
