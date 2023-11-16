package com.dfa.dfaserver.invest.service.integration;

import com.dfa.dfaserver.invest.domain.Asset;
import lombok.Data;

@Data
public class Balance {
    public Asset asset;
    public Integer balance;
}
