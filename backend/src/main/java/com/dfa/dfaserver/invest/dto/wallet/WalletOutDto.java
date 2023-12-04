package com.dfa.dfaserver.invest.dto.wallet;

import com.dfa.dfaserver.invest.domain.Account;
import com.dfa.dfaserver.invest.dto.account.AccountDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class WalletOutDto {

    @NotNull
    @NotBlank
    public String userId;

    @NotNull
    public List<AccountDto> accounts;
}
