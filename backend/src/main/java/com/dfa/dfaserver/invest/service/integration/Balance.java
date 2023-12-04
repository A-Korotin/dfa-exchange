package com.dfa.dfaserver.invest.service.integration;

import com.dfa.dfaserver.invest.domain.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Balance {
    public Asset asset;
    public Integer balance;
}
