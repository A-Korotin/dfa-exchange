package com.dfa.dfaserver.invest.repository.order;

import com.dfa.dfaserver.invest.domain.order.MarketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MarketOrderRepository extends JpaRepository<MarketOrder, UUID> {
}
