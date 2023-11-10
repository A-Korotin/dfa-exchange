package com.dfa.dfaserver.invest.factory.order;

import com.dfa.dfaserver.invest.domain.order.BuyMarketOrder;
import com.dfa.dfaserver.invest.domain.order.MarketOrder;
import com.dfa.dfaserver.invest.domain.order.OrderType;
import com.dfa.dfaserver.invest.domain.order.SellMarketOrder;
import com.dfa.dfaserver.invest.dto.order.MarketOrderDto;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

@Component
public class MarketOrderFactory extends OrderFactory<MarketOrder, MarketOrderDto> {
    {
        supplierMap.put(OrderType.BUY, BuyMarketOrder::new);
        supplierMap.put(OrderType.SELL, SellMarketOrder::new);
    }

    @Override
    @ObjectFactory
    public MarketOrder create(MarketOrderDto dto) {
        return supplierMap.get(dto.getType()).get();

    }
}
