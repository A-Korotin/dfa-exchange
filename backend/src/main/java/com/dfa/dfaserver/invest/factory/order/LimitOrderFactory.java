package com.dfa.dfaserver.invest.factory.order;

import com.dfa.dfaserver.invest.domain.order.BuyLimitOrder;
import com.dfa.dfaserver.invest.domain.order.LimitOrder;
import com.dfa.dfaserver.invest.domain.order.OrderType;
import com.dfa.dfaserver.invest.domain.order.SellLimitOrder;
import com.dfa.dfaserver.invest.dto.order.InputLimitOrderDto;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

@Component
public class LimitOrderFactory extends OrderFactory<LimitOrder, InputLimitOrderDto> {
    {
        supplierMap.put(OrderType.SELL, SellLimitOrder::new);
        supplierMap.put(OrderType.BUY, BuyLimitOrder::new);
    }
    @Override
    @ObjectFactory
    public LimitOrder create(InputLimitOrderDto dto) {
        return supplierMap.get(dto.getType()).get();
    }
}
