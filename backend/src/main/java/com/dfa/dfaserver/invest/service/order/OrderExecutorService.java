package com.dfa.dfaserver.invest.service.order;

import com.dfa.dfaserver.invest.domain.order.LimitOrder;
import com.dfa.dfaserver.invest.domain.order.MarketOrder;

public interface OrderExecutorService {
    void placeSellLimitOrder(LimitOrder limitOrder);
    void placeBuyLimitOrder(LimitOrder limitOrder);
    void placeSellMarketOrder(MarketOrder marketOrder);
    void placeBuyMarketOrder(MarketOrder marketOrder);
}
