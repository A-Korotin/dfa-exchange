package com.dfa.dfaserver.invest.mapper.asset;

import com.dfa.dfaserver.invest.domain.Asset;
import com.dfa.dfaserver.invest.dto.asset.AssetDto;
import com.dfa.dfaserver.invest.mapper.config.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapConfig.class)
public abstract class AssetMapper {

    @Mapping(target = "chain.name", source = "chainName")
    public abstract Asset fromDto(AssetDto dto);

    @Mapping(target = "chainName", source = "chain.name")
    public abstract AssetDto toDto(Asset asset);


}
