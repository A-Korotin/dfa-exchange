package com.dfa.dfaserver.invest.domain;

import com.dfa.dfaserver.invest.domain.repository.AccountRepository;
import com.dfa.dfaserver.invest.exception.factory.UnknownChainException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ComponentScan("com.dfa.dfaserver.invest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountListenerTest {
    @Autowired
    private AccountRepository accountRepository;

    private OISChain validChain;
    private OISChain invalidChain;

    @BeforeAll
    public void setup() {
        validChain = new OISChain("TEST");
        invalidChain = new OISChain("INVALID");
    }

    @Test
    public void positiveListenerTest() {
        Account account = new Account();
        account.setAddress("VALID_ADDR");
        account.setPrivateKey("PRIVATE");
        account.setAlias("alias");
        account.setChain(validChain);

        account = accountRepository.save(account);

        assertEquals(2, account.getAssets().size());
        assertEquals("BTC", account.getAssets().get(0).getTicker());
        assertEquals("TRX", account.getAssets().get(1).getTicker());
    }

    @Test
    public void negativeListenerTest() {
        Account account = new Account();
        account.setAddress("VALID_ADDR2");
        account.setPrivateKey("PRIVATE");
        account.setAlias("alias");
        account.setChain(invalidChain);

        assertThrows(UnknownChainException.class, () -> accountRepository.save(account));
    }

}
