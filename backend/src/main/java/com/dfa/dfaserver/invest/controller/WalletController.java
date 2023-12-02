package com.dfa.dfaserver.invest.controller;


import com.dfa.dfaserver.invest.domain.Wallet;
import com.dfa.dfaserver.invest.dto.error.ErrorDto;
import com.dfa.dfaserver.invest.dto.wallet.WalletInDto;
import com.dfa.dfaserver.invest.dto.wallet.WalletOutDto;
import com.dfa.dfaserver.invest.exception.NotFoundException;
import com.dfa.dfaserver.invest.mapper.WalletMapper;
import com.dfa.dfaserver.invest.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wallets")
@Validated
@Tag(name = "Wallets", description = "Create, retrieve, update and delete wallets")
public class WalletController {

    private final WalletService walletService;

    private final WalletMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create wallet", description = "Create wallet for user",
            responses = {@ApiResponse(description = "Created", responseCode = "201", useReturnTypeSchema = true),
                    @ApiResponse(description = "Bad request", responseCode = "400"),
                    @ApiResponse(description = "Not authorized", responseCode = "401")})
    public WalletOutDto createWallet(@Valid @RequestBody WalletInDto dto) {
        Wallet wallet = mapper.fromDto(dto);
        wallet = walletService.save(wallet);

        return mapper.toDto(wallet);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get wallet",
            description = "Get wallet by ID",
            responses = {@ApiResponse(description = "OK", responseCode = "200", useReturnTypeSchema = true),
                    @ApiResponse(description = "Bad request", responseCode = "400"),
                    @ApiResponse(description = "Not authorized", responseCode = "401"),
                    @ApiResponse(description = "Not found", responseCode = "404")})
    public WalletOutDto getWallet(@PathVariable UUID id) {
        Wallet wallet = walletService.findById(id)
                .orElseThrow(() -> new NotFoundException("Wallet with id '%s' could not be found", id));

        return mapper.toDto(wallet);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Edit wallet",
            description = "Edit wallet by ID",
            responses = {@ApiResponse(description = "OK", responseCode = "200", useReturnTypeSchema = true),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDto.class))}),
                    @ApiResponse(description = "Not authorized", responseCode = "401", content = {@Content(schema = @Schema(implementation = Void.class))}),
                    @ApiResponse(description = "Not found", responseCode = "404", content = {@Content(schema = @Schema(implementation = Void.class))})})
    public WalletOutDto editWallet(@PathVariable UUID id,
                                        @Valid @RequestBody WalletInDto dto) {
        Wallet wallet = mapper.fromDto(dto);
        System.out.println(wallet.toString());
        wallet = walletService.updateById(id, wallet);

        return mapper.toDto(wallet);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete wallet",
            description = "Delete and cancel wallet by ID",
            responses = {@ApiResponse(description = "Deleted", responseCode = "204", useReturnTypeSchema = true),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDto.class))}),
                    @ApiResponse(description = "Not authorized", responseCode = "401", content = {@Content(schema = @Schema(implementation = Void.class))}),
                    @ApiResponse(description = "Not found", responseCode = "404", content = {@Content(schema = @Schema(implementation = Void.class))})})
    public void deleteLimitOrder(@PathVariable UUID id) {
        walletService.deleteById(id);
    }

}
