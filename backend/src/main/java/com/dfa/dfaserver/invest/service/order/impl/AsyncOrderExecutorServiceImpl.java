package com.dfa.dfaserver.invest.service.order.impl;

import com.dfa.dfaserver.invest.domain.order.LimitOrder;
import com.dfa.dfaserver.invest.domain.order.MarketOrder;
import com.dfa.dfaserver.invest.service.order.OrderExecutorService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncOrderExecutorServiceImpl implements OrderExecutorService {

    @Override
    @Async
    public void placeSellLimitOrder(LimitOrder limitOrder) {

    }

    @Override
    public void placeBuyLimitOrder(LimitOrder limitOrder) {

    }

    @Override
    public void placeSellMarketOrder(MarketOrder marketOrder) {

    }

    @Override
    public void placeBuyMarketOrder(MarketOrder marketOrder) {

    }
}
