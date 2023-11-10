package com.dfa.dfaserver.invest.dto.order;

import com.dfa.dfaserver.invest.domain.order.OrderStatus;
import com.dfa.dfaserver.invest.domain.order.OrderType;
import com.dfa.dfaserver.invest.dto.asset.AssetDto;
import com.dfa.dfaserver.invest.validation.groups.OnCreate;
import com.dfa.dfaserver.invest.validation.groups.OnUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
public class LimitOrderDto {

    @Null(groups = {OnCreate.class, OnUpdate.class})
    public UUID id;

    @NotNull(groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    public OrderType type;

    @Null(groups = {OnCreate.class, OnUpdate.class})
    public OrderStatus status;

    @Null(groups = {OnCreate.class, OnUpdate.class})
    public ZonedDateTime timestamp;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    public String from;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    public String to;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    public AssetDto buyAsset;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    public AssetDto sellAsset;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @Min(1)
    public Integer price;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @Min(1)
    public Integer volume;

    @Null(groups = {OnCreate.class, OnUpdate.class})
    public Integer volumeExecuted;
}
