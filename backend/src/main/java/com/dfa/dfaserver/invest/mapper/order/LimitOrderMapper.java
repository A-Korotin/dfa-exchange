package com.dfa.dfaserver.invest.mapper.order;

import com.dfa.dfaserver.invest.domain.order.LimitOrder;
import com.dfa.dfaserver.invest.dto.order.LimitOrderDto;
import com.dfa.dfaserver.invest.dto.order.OutputLimitOrderDto;
import com.dfa.dfaserver.invest.factory.order.LimitOrderFactory;
import com.dfa.dfaserver.invest.mapper.asset.AssetMapper;
import com.dfa.dfaserver.invest.mapper.config.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapConfig.class, uses = {AssetMapper.class, LimitOrderFactory.class})
public abstract class LimitOrderMapper {

    @Mapping(target = "from.address", source = "from")
    @Mapping(target = "to.address", source = "to")
    public abstract LimitOrder fromDto(LimitOrderDto dto);

    @Mapping(target = "from", source = "from.address")
    @Mapping(target = "to", source = "to.address")
    public abstract OutputLimitOrderDto toDto(LimitOrder order);
}
