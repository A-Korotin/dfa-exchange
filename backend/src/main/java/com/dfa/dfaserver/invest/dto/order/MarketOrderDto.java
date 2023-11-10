package com.dfa.dfaserver.invest.dto.order;

import com.dfa.dfaserver.invest.domain.order.OrderStatus;
import com.dfa.dfaserver.invest.domain.order.OrderType;
import com.dfa.dfaserver.invest.dto.asset.AssetDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
public class MarketOrderDto {
    @Null
    public UUID id;

    @NotNull
    public OrderType type;

    @Null
    public OrderStatus status;

    @Null
    public ZonedDateTime timestamp;

    @NotNull
    public String from;

    @NotNull
    public String to;

    @NotNull
    public AssetDto buyAsset;

    @NotNull
    public AssetDto sellAsset;

    @NotNull
    @Min(1)
    public Integer volume;
}
