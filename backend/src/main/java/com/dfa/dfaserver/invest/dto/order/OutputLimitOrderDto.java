package com.dfa.dfaserver.invest.dto.order;

import com.dfa.dfaserver.invest.domain.order.OrderStatus;
import com.dfa.dfaserver.invest.domain.order.OrderType;
import com.dfa.dfaserver.invest.dto.asset.AssetDto;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class OutputLimitOrderDto {
    public UUID id;
    public OrderType type;
    public OrderStatus status;
    public ZonedDateTime timestamp;
    public String from;
    public String to;
    public AssetDto buyAsset;
    public AssetDto sellAsset;
    public Integer price;
}
