package com.dfa.dfaserver.invest.service.integration.impl.klever.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KleverBalanceDto {
    public String assetId;
    public Integer balance;
}
