package com.dfa.dfaserver.domain.transaction;

import com.dfa.dfaserver.domain.Account;
import com.dfa.dfaserver.domain.Asset;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account sender;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account receiver;

    private String signature;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ticker", column = @Column(name = "asset_ticker")),
            @AttributeOverride(name = "chain.name", column = @Column(name = "asset_chain"))
    })
    private Asset asset;

    private int amount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @CreationTimestamp
    private ZonedDateTime timestamp;
}
