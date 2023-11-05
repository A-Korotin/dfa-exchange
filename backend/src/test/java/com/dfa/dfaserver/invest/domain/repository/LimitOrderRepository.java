package com.dfa.dfaserver.invest.domain.repository;

import com.dfa.dfaserver.invest.domain.order.LimitOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LimitOrderRepository extends CrudRepository<LimitOrder, UUID> {
}
