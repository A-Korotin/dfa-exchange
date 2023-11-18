package com.dfa.dfaserver.invest.service.integration.impl.klever.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // as API returns lots of stuff that we don't need
public class KleverAssetDto {
    public String ticker;
    public String name;
    public String assetType;
    public String assetId;

}
