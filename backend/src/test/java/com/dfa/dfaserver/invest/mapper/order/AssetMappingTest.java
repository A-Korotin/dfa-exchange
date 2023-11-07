package com.dfa.dfaserver.invest.mapper.order;


import com.dfa.dfaserver.invest.domain.Asset;
import com.dfa.dfaserver.invest.dto.asset.AssetDto;
import com.dfa.dfaserver.invest.mapper.asset.AssetMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AssetMappingTest {

    @Autowired
    private AssetMapper mapper;

    @Test
    @DisplayName("Test mapping AssetDto -> Asset and vice versa")
    public void test() {
        AssetDto assetDto = AssetDto.builder().chainName("TEST").ticker("TICK").build();
        Asset asset = mapper.fromDto(assetDto);
        assertEquals(assetDto.chainName, asset.getChain().getName());
        assertEquals(assetDto.ticker, asset.getTicker());

        assetDto = mapper.toDto(asset);
        assertEquals(assetDto.chainName, asset.getChain().getName());
        assertEquals(assetDto.ticker, asset.getTicker());
    }
}
