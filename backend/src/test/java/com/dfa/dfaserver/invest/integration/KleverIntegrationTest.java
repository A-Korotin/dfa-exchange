package com.dfa.dfaserver.invest.integration;

import com.dfa.dfaserver.invest.service.integration.impl.klever.KleverOISProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KleverIntegrationTest {

    @Autowired
    private KleverOISProvider oisProvider;


    @Test
    @DisplayName("Test Klever API asset fetching")
    public void testAssetHttpRequest() {
        var assets = oisProvider.getAssets().join();
        assertTrue(assets.size() > 0, "Asset list should be non-empty");
    }
}
