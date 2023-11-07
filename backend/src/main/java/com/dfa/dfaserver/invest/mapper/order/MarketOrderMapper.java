package com.dfa.dfaserver.invest.mapper.order;

import com.dfa.dfaserver.invest.domain.order.MarketOrder;
import com.dfa.dfaserver.invest.dto.order.MarketOrderDto;
import com.dfa.dfaserver.invest.dto.order.OutputMarketOrderDto;
import com.dfa.dfaserver.invest.factory.order.MarketOrderFactory;
import com.dfa.dfaserver.invest.mapper.asset.AssetMapper;
import com.dfa.dfaserver.invest.mapper.config.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapConfig.class, uses = {MarketOrderFactory.class, AssetMapper.class})
public abstract class MarketOrderMapper {
    @Mapping(target = "from.address", source = "fromAddress")
    @Mapping(target = "to.address", source = "toAddress")
    public abstract MarketOrder fromDto(MarketOrderDto dto);

    @Mapping(target = "fromAddress", source = "from.address")
    @Mapping(target = "toAddress", source = "to.address")
    public abstract OutputMarketOrderDto toDto(MarketOrder order);
}
