package com.dfa.dfaserver.invest.mapper;

import com.dfa.dfaserver.invest.domain.Wallet;
import com.dfa.dfaserver.invest.dto.wallet.WalletInDto;
import com.dfa.dfaserver.invest.dto.wallet.WalletOutDto;
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
public class WalletMappingTest {

    @Autowired
    private WalletMapper mapper;

    @Test
    @DisplayName("Test mapping WalletInDto -> Wallet -> WalletOutDto")
    public void test() {
        WalletInDto walletInDto = WalletInDto.builder().userId("Lox Pedalniy").build();
        Wallet wallet = mapper.fromDto(walletInDto);
        assertEquals(walletInDto.userId, wallet.getUserId());

        WalletOutDto walletOutDto = mapper.toDto(wallet);
        assertEquals(walletOutDto.userId, walletOutDto.userId);
    }
}
