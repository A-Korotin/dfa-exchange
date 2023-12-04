package com.dfa.dfaserver.invest.dto.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class AccountDto {

    @NotNull
    @NotBlank
    public String address;

    @NotNull
    @NotBlank
    public String alias;

    @NotNull
    @NotBlank
    public String chain;

    @NotNull
    public UUID wallet;
}
