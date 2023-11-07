package com.dfa.dfaserver.invest.dto.order;

import com.dfa.dfaserver.invest.domain.order.OrderType;
import com.dfa.dfaserver.invest.dto.asset.AssetDto;
import lombok.Data;

@Data
public class MarketOrderDto {
    public OrderType type;
    public String fromAddress;
    public String toAddress;
    public AssetDto buyAsset;
    public AssetDto sellAsset;
}
