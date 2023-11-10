package com.dfa.dfaserver.invest.factory.order;

import com.dfa.dfaserver.invest.domain.order.BuyMarketOrder;
import com.dfa.dfaserver.invest.domain.order.MarketOrder;
import com.dfa.dfaserver.invest.domain.order.OrderType;
import com.dfa.dfaserver.invest.domain.order.SellMarketOrder;
import com.dfa.dfaserver.invest.dto.order.InputMarketOrderDto;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

@Component
public class MarketOrderFactory extends OrderFactory<MarketOrder, InputMarketOrderDto> {
    {
        supplierMap.put(OrderType.BUY, BuyMarketOrder::new);
        supplierMap.put(OrderType.SELL, SellMarketOrder::new);
    }

    @Override
    @ObjectFactory
    public MarketOrder create(InputMarketOrderDto dto) {
        return supplierMap.get(dto.getType()).get();

    }
}
