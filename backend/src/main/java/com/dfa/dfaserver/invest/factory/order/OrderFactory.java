package com.dfa.dfaserver.invest.factory.order;

import com.dfa.dfaserver.invest.domain.order.Order;
import com.dfa.dfaserver.invest.domain.order.OrderType;

import java.util.Map;
import java.util.function.Supplier;

public abstract class OrderFactory<T extends Order, D> {
    protected Map<OrderType, Supplier<T>> supplierMap;

    public abstract T create(D dto);
}
