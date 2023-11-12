package com.dfa.dfaserver.invest.controller.order;

import com.dfa.dfaserver.invest.domain.order.LimitOrder;
import com.dfa.dfaserver.invest.dto.error.ErrorDto;
import com.dfa.dfaserver.invest.dto.order.InputLimitOrderDto;
import com.dfa.dfaserver.invest.dto.order.LimitOrderDto;
import com.dfa.dfaserver.invest.exception.NotFoundException;
import com.dfa.dfaserver.invest.mapper.order.LimitOrderMapper;
import com.dfa.dfaserver.invest.service.order.LimitOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

// TODO: 10.11.2023 Order execution logic 
@RestController
@RequestMapping("/api/v1/orders/limit")
@RequiredArgsConstructor
@Validated
@Tag(name = "Limit orders", description = "Create, retrieve, update and delete limit orders")
public class LimitOrderController {
    private final LimitOrderService limitOrderService;
    private final LimitOrderMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create limit order",
            description = "Create limit order and automatically send it to the execution queue",
            responses = {@ApiResponse(description = "Created", responseCode = "201", useReturnTypeSchema = true),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDto.class))}),
                    @ApiResponse(description = "Not authorized", responseCode = "401", content = {@Content(schema = @Schema(implementation = Void.class))})})
    public LimitOrderDto createLimitOrder(@Valid @RequestBody InputLimitOrderDto dto) {
        LimitOrder order = mapper.fromDto(dto);
        order = limitOrderService.save(order);
        return mapper.toDto(order);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get limit order",
            description = "Get limit order by ID",
            responses = {@ApiResponse(description = "OK", responseCode = "200", useReturnTypeSchema = true),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDto.class))}),
                    @ApiResponse(description = "Not authorized", responseCode = "401", content = {@Content(schema = @Schema(implementation = Void.class))}),
                    @ApiResponse(description = "Not found", responseCode = "404", content = {@Content(schema = @Schema(implementation = Void.class))})})
    public LimitOrderDto getLimitOrder(@PathVariable UUID id) {
        LimitOrder order = limitOrderService.findById(id)
                .orElseThrow(() -> new NotFoundException("Limit order with id '%s' could not be found", id));

        return mapper.toDto(order);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit limit order",
            description = "Edit limit order by ID",
            responses = {@ApiResponse(description = "OK", responseCode = "200", useReturnTypeSchema = true),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDto.class))}),
                    @ApiResponse(description = "Not authorized", responseCode = "401", content = {@Content(schema = @Schema(implementation = Void.class))}),
                    @ApiResponse(description = "Not found", responseCode = "404", content = {@Content(schema = @Schema(implementation = Void.class))})})
    public LimitOrderDto editLimitOrder(@PathVariable UUID id,
                                        @Valid @RequestBody InputLimitOrderDto dto) {
        LimitOrder order = mapper.fromDto(dto);
        order = limitOrderService.updateById(id, order);
        return mapper.toDto(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete limit order",
            description = "Delete and cancel limit order by ID",
            responses = {@ApiResponse(description = "Deleted", responseCode = "204", useReturnTypeSchema = true),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDto.class))}),
                    @ApiResponse(description = "Not authorized", responseCode = "401", content = {@Content(schema = @Schema(implementation = Void.class))}),
                    @ApiResponse(description = "Not found", responseCode = "404", content = {@Content(schema = @Schema(implementation = Void.class))})})
    public void deleteLimitOrder(@PathVariable UUID id) {
        limitOrderService.deleteById(id);
    }

    @GetMapping
    @Operation(summary = "Get all limit orders",
            description = "Get all limit orders by certain criteria specified by request parameters",
            responses = {@ApiResponse(description = "OK", responseCode = "200", useReturnTypeSchema = true),
                    @ApiResponse(description = "Not authorized", responseCode = "401", content = {@Content(schema = @Schema(implementation = Void.class))})})
    @PageableAsQueryParam
    public List<LimitOrderDto> getAllLimitOrders(Pageable query) {
        return limitOrderService.findAll(query).stream()
                .map(mapper::toDto)
                .toList();
    }
}
