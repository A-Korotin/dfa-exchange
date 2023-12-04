package com.dfa.dfaserver.invest.mapper;

import com.dfa.dfaserver.invest.domain.Wallet;
import com.dfa.dfaserver.invest.dto.wallet.WalletInDto;
import com.dfa.dfaserver.invest.dto.wallet.WalletOutDto;
import org.mapstruct.Mapper;

@Mapper(config=MapConfig.class, uses = AccountMapper.class)
public abstract class WalletMapper {

    public abstract Wallet fromDto(WalletInDto dto);

    public abstract WalletOutDto toDto(Wallet wallet);
}
