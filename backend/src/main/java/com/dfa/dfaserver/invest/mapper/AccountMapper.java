package com.dfa.dfaserver.invest.mapper;

import com.dfa.dfaserver.invest.domain.Account;
import com.dfa.dfaserver.invest.dto.account.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config=MapConfig.class)
public abstract class AccountMapper {

    @Mapping(target = "chain.name", source = "chain")
    @Mapping(target = "wallet.id", source = "wallet")
    public abstract Account fromDto(AccountDto dto);

    @Mapping(target = "chain", source = "chain.name")
    @Mapping(target = "wallet", source = "wallet.id")
    public abstract AccountDto toDto(Account account);
}
