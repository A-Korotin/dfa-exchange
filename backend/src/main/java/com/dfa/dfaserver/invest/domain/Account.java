package com.dfa.dfaserver.invest.domain;

import com.dfa.dfaserver.invest.domain.listeners.AccountInitializerListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@EntityListeners(AccountInitializerListener.class)
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
