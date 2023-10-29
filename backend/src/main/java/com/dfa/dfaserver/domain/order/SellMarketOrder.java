package com.dfa.dfaserver.domain.order;

import com.dfa.dfaserver.service.order.OrderExecutorService;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("SELL")
public class SellMarketOrder extends MarketOrder {
    @Override
    public void execute(OrderExecutorService visitor) {
        visitor.placeSellMarketOrder(this);
    }
}
