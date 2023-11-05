package com.dfa.dfaserver.invest.domain.order;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "market_order")
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class MarketOrder extends Order {
}
