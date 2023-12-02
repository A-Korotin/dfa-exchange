package com.dfa.dfaserver.invest.mapper;

import com.dfa.dfaserver.invest.domain.Account;
import com.dfa.dfaserver.invest.dto.account.AccountDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountMappingTest {

    @Autowired
    private AccountMapper mapper;

    @Test
    @DisplayName("Test mapping AccountDto -> Account -> AccountDto")
    public void test() {
        AccountDto accountDto = AccountDto.builder()
                .address("12345")
                .wallet(UUID.fromString("3a495179-a031-4c35-b6d7-0cadcb097ad0"))
                .alias("friend")
                .chain("some ois name")
                .build();

        Account account = mapper.fromDto(accountDto);

        assertEquals(accountDto.address, account.getAddress());
        assertEquals(accountDto.alias, account.getAlias());
        assertEquals(accountDto.wallet, account.getWallet().getId());
        assertEquals(accountDto.chain, account.getChain().getName());

        account = mapper.fromDto(accountDto);

        assertEquals(account.getAddress(), accountDto.address);
        assertEquals(account.getAlias(), accountDto.alias);
        assertEquals(account.getWallet().getId(), accountDto.wallet);
        assertEquals(account.getChain().getName(), accountDto.chain);
    }
}
