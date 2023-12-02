package com.dfa.dfaserver.invest.controller;

import com.dfa.dfaserver.invest.domain.Account;
import com.dfa.dfaserver.invest.dto.account.AccountDto;
import com.dfa.dfaserver.invest.dto.error.ErrorDto;
import com.dfa.dfaserver.invest.exception.NotFoundException;
import com.dfa.dfaserver.invest.mapper.AccountMapper;
import com.dfa.dfaserver.invest.service.AccountService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
@Validated
@Tag(name = "Accounts", description = "Create, retrieve, update and delete accounts")
public class AccountController {

    private final AccountService accountService;

    private final AccountMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create account",
            description = "Create account",
            responses = {@ApiResponse(description = "Created", responseCode = "201", useReturnTypeSchema = true),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDto.class))}),
                    @ApiResponse(description = "Not authorized", responseCode = "401", content = {@Content(schema = @Schema(implementation = Void.class))})})
    public AccountDto createLimitOrder(@Valid @RequestBody AccountDto dto) {
        Account account = mapper.fromDto(dto);
        System.out.println(account.toString());
        account = accountService.save(account);

        return mapper.toDto(account);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get account",
            description = "Get account by ID",
            responses = {@ApiResponse(description = "OK", responseCode = "200", useReturnTypeSchema = true),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDto.class))}),
                    @ApiResponse(description = "Not authorized", responseCode = "401", content = {@Content(schema = @Schema(implementation = Void.class))}),
                    @ApiResponse(description = "Not found", responseCode = "404", content = {@Content(schema = @Schema(implementation = Void.class))})})
    public AccountDto getLimitOrder(@PathVariable String id) {
        Account account = accountService.findById(id)
                .orElseThrow(() -> new NotFoundException("Account with id '%s' could not be found", id));

        return mapper.toDto(account);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Edit account",
            description = "Edit account by ID",
            responses = {@ApiResponse(description = "OK", responseCode = "200", useReturnTypeSchema = true),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDto.class))}),
                    @ApiResponse(description = "Not authorized", responseCode = "401", content = {@Content(schema = @Schema(implementation = Void.class))}),
                    @ApiResponse(description = "Not found", responseCode = "404", content = {@Content(schema = @Schema(implementation = Void.class))})})
    public AccountDto editLimitOrder(@PathVariable String id,
                                        @Valid @RequestBody AccountDto dto) {
        Account account = mapper.fromDto(dto);
        account = accountService.updateById(id, account);

        return mapper.toDto(account);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete account",
            description = "Delete and cancel account by ID",
            responses = {@ApiResponse(description = "Deleted", responseCode = "204", useReturnTypeSchema = true),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDto.class))}),
                    @ApiResponse(description = "Not authorized", responseCode = "401", content = {@Content(schema = @Schema(implementation = Void.class))}),
                    @ApiResponse(description = "Not found", responseCode = "404", content = {@Content(schema = @Schema(implementation = Void.class))})})
    public void deleteLimitOrder(@PathVariable String id) {
        accountService.deleteById(id);
    }

}
