package com.dfa.dfaserver.domain.order;

import com.dfa.dfaserver.domain.Account;
import com.dfa.dfaserver.domain.Asset;
import com.dfa.dfaserver.service.order.OrderExecutorService;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;


@MappedSuperclass
@Data
@DiscriminatorColumn(name = "type")
public abstract class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @Enumerated(EnumType.STRING)
    protected OrderType type;

    @Enumerated(EnumType.STRING)
    protected OrderStatus status;

    @CreationTimestamp
    protected ZonedDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    protected Account from;

    @ManyToOne(fetch = FetchType.LAZY)
    protected Account to;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ticker", column = @Column(name = "buy_asset_ticker")),
            @AttributeOverride(name = "chain.name", column = @Column(name = "buy_asset_chain"))
    })
    protected Asset buyAsset;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ticker", column = @Column(name = "sell_asset_ticker")),
            @AttributeOverride(name = "chain.name", column = @Column(name = "sell_asset_chain"))
    })
    protected Asset sellAsset;

    public abstract void execute(OrderExecutorService visitor);
}
