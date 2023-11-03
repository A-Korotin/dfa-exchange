package com.dfa.dfaserver.domain.repository;

import com.dfa.dfaserver.domain.order.LimitOrder;
import com.dfa.dfaserver.domain.order.MarketOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MarketOrderRepository extends CrudRepository<MarketOrder, UUID> {
}
