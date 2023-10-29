package com.dfa.dfaserver.domain.order;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "limit_orders")
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class LimitOrder extends Order {
    protected Integer price;
}
