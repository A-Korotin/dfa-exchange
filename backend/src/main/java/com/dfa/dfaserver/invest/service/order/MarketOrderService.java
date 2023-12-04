package com.dfa.dfaserver.invest.service.order;

import com.dfa.dfaserver.invest.domain.order.MarketOrder;
import com.dfa.dfaserver.invest.service.CrudService;
import com.dfa.dfaserver.invest.service.PageableService;

import java.util.UUID;

public interface MarketOrderService extends CrudService<MarketOrder, UUID>, PageableService<MarketOrder> {
}
