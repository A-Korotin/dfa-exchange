package com.dfa.dfaserver.domain.order;

import com.dfa.dfaserver.service.order.OrderExecutorService;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("BUY")
public class BuyMarketOrder extends MarketOrder {
    @Override
    public void execute(OrderExecutorService visitor) {
        visitor.placeBuyMarketOrder(this);
    }
}
