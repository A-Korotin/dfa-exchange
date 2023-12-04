package com.dfa.dfaserver.invest.mapper.order;


import com.dfa.dfaserver.invest.domain.Asset;
import com.dfa.dfaserver.invest.dto.asset.AssetDto;
import com.dfa.dfaserver.invest.mapper.asset.AssetMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
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
