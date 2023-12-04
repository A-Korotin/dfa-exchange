package com.dfa.dfaserver.invest.domain;

import com.dfa.dfaserver.invest.domain.mock.MockOrderExecutorService;
import com.dfa.dfaserver.invest.domain.order.*;
import com.dfa.dfaserver.invest.domain.repository.AccountRepository;
import com.dfa.dfaserver.invest.repository.order.LimitOrderRepository;
import com.dfa.dfaserver.invest.repository.order.MarketOrderRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ComponentScan("com.dfa.dfaserver.invest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderJpaTest {
    @Autowired
    private MockOrderExecutorService orderExecutorService;

    @Autowired
    private LimitOrderRepository limitOrderRepository;

    @Autowired
    private MarketOrderRepository marketOrderRepository;

    @Autowired
    private AccountRepository testAccountRepository;


    private Asset asset;
    private Account fromAccount;
    private Account toAccount;


    private void init() {
        final OISChain chain = new OISChain();
        chain.setName("TEST");
        asset = new Asset();
        asset.setChain(chain);
        asset.setTicker("TICK");


        fromAccount = new Account();
        fromAccount.setAddress("ADDR_FROM");
        fromAccount.setAlias("Test account");
        fromAccount.setChain(chain);
        fromAccount.setPrivateKey("PRIVATE_KEY");
        fromAccount = testAccountRepository.save(fromAccount);

        toAccount = new Account();
        toAccount.setAddress("ADDR_TO");
        toAccount.setAlias("Test account");
        toAccount.setChain(chain);
        toAccount.setPrivateKey("PRIVATE_KEY");
        toAccount = testAccountRepository.save(toAccount);
    }

    private void initOrder(Order order) {
        order.setType(OrderType.SELL);
        order.setBuyAsset(asset);
        order.setSellAsset(asset);
        order.setFrom(fromAccount);
        order.setTo(toAccount);
        order.setStatus(OrderStatus.PLACED);
    }

    private void initMarketOrder(MarketOrder order) {
        initOrder(order);
    }

    private void initLimitOrder(LimitOrder order) {
        initOrder(order);
        order.setPrice(1);
    }


    @BeforeAll
    public void setupOrders() {
        init();

        SellMarketOrder sellMarketOrder = new SellMarketOrder();
        initMarketOrder(sellMarketOrder);

        BuyMarketOrder buyMarketOrder = new BuyMarketOrder();
        initMarketOrder(buyMarketOrder);

        SellLimitOrder sellLimitOrder = new SellLimitOrder();
        initLimitOrder(sellLimitOrder);

        BuyLimitOrder buyLimitOrder = new BuyLimitOrder();
        initLimitOrder(buyLimitOrder);

        limitOrderRepository.save(sellLimitOrder);
        limitOrderRepository.save(buyLimitOrder);
        marketOrderRepository.save(buyMarketOrder);
        marketOrderRepository.save(sellMarketOrder);
    }

    @Test
    public void polymorphicQueryTest() {
        Iterable<MarketOrder> marketOrders = marketOrderRepository.findAll();

        marketOrders.forEach(o -> o.execute(orderExecutorService));

        Iterable<LimitOrder> limitOrders = limitOrderRepository.findAll();

        limitOrders.forEach(o -> o.execute(orderExecutorService));

        assertEquals(1, orderExecutorService.getBuyLimitExecutions());
        assertEquals(1, orderExecutorService.getSellLimitExecutions());
        assertEquals(1, orderExecutorService.getBuyMarketExecutions());
        assertEquals(1, orderExecutorService.getSellMarketExecutions());
    }

}
