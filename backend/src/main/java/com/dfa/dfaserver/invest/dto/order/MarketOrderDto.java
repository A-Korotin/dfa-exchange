package com.dfa.dfaserver.invest.dto.order;

import com.dfa.dfaserver.invest.domain.order.OrderType;
import com.dfa.dfaserver.invest.dto.asset.AssetDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MarketOrderDto {
    public OrderType type;
    public String from;
    public String to;
    public AssetDto buyAsset;
    public AssetDto sellAsset;
}
