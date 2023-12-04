package com.dfa.dfaserver.invest.domain.order;

import com.dfa.dfaserver.invest.domain.Account;
import com.dfa.dfaserver.invest.domain.Asset;
import com.dfa.dfaserver.invest.service.order.OrderExecutorService;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;


@MappedSuperclass
@Data
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "dtype", columnDefinition = "TEXT", insertable = false, updatable = false)
    protected OrderType type; // readonly, as it's a discriminator column

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

    protected Integer volume;

    protected Integer volumeExecuted;

    public abstract void execute(OrderExecutorService visitor);
}
