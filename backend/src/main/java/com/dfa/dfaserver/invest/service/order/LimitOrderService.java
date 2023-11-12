package com.dfa.dfaserver.invest.service.order;

import com.dfa.dfaserver.invest.domain.order.LimitOrder;
import com.dfa.dfaserver.invest.service.CrudService;
import com.dfa.dfaserver.invest.service.PageableService;

import java.util.UUID;

public interface LimitOrderService extends CrudService<LimitOrder, UUID>, PageableService<LimitOrder> {
}
