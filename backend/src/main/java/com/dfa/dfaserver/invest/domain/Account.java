package com.dfa.dfaserver.invest.domain;

import com.dfa.dfaserver.invest.domain.listener.AccountInitializerListener;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@EntityListeners(AccountInitializerListener.class)
@Table(name = "account")
@Data
public class Account {
    @Id
    private String address;

    private String privateKey;

    private String alias;

    @Transient
    private List<Asset> assets;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "chain_name"))
    })
    private OISChain chain;

    @ManyToOne
    private Wallet wallet;
}
