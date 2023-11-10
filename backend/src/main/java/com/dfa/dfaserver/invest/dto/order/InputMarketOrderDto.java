package com.dfa.dfaserver.invest.dto.order;

import com.dfa.dfaserver.invest.domain.order.OrderType;
import com.dfa.dfaserver.invest.dto.asset.AssetDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InputMarketOrderDto {

    @NotNull
    public OrderType type;

    @NotNull
    @NotBlank
    public String from;

    @NotNull
    @NotBlank
    public String to;

    @NotNull
    public AssetDto buyAsset;

    @NotNull
    public AssetDto sellAsset;

    @NotNull
    @Min(1)
    public Integer volume;
}
