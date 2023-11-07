package com.dfa.dfaserver.invest.dto.order;

import com.dfa.dfaserver.invest.domain.order.OrderType;
import com.dfa.dfaserver.invest.dto.asset.AssetDto;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LimitOrderDto {
    public OrderType type;
    public String from;
    public String to;
    public AssetDto buyAsset;
    public AssetDto sellAsset;
    @Min(1)
    public Integer price;
}
