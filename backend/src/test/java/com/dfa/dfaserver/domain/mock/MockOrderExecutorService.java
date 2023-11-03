package com.dfa.dfaserver.domain.mock;

import com.dfa.dfaserver.domain.order.LimitOrder;
import com.dfa.dfaserver.domain.order.MarketOrder;
import com.dfa.dfaserver.service.order.OrderExecutorService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MockOrderExecutorService implements OrderExecutorService {

    private int sellLimitExecutions = 0;
    private int buyLimitExecutions = 0;
    private int sellMarketExecutions = 0;
    private int buyMarketExecutions = 0;

    @Override
    public void placeSellLimitOrder(LimitOrder limitOrder) {
        sellLimitExecutions++;
    }

    @Override
    public void placeBuyLimitOrder(LimitOrder limitOrder) {
        buyLimitExecutions++;
    }

    @Override
    public void placeSellMarketOrder(MarketOrder marketOrder) {
        sellMarketExecutions++;
    }

    @Override
    public void placeBuyMarketOrder(MarketOrder marketOrder) {
        buyMarketExecutions++;
    }

    public int getSellLimitExecutions() {
        return sellLimitExecutions;
    }

    public int getBuyLimitExecutions() {
        return buyLimitExecutions;
    }

    public int getSellMarketExecutions() {
        return sellMarketExecutions;
    }

    public int getBuyMarketExecutions() {
        return buyMarketExecutions;
    }
}
