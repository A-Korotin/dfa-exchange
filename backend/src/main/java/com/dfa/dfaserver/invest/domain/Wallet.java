package com.dfa.dfaserver.invest.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "wallet")
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String userId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wallet")
    private List<Account> accounts;
}
