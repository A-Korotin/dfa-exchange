package com.dfa.dfaserver.invest.dto.asset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssetDto {
    @NotNull
    @NotBlank
    public String ticker;

    @NotNull
    @NotBlank
    public String chainName;
}
