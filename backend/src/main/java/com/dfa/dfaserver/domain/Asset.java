package com.dfa.dfaserver.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Asset {
    String ticker;
    OISChain chain;
}
