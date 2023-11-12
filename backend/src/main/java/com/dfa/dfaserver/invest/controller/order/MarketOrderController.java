package com.dfa.dfaserver.invest.controller.order;

import com.dfa.dfaserver.invest.domain.order.MarketOrder;
import com.dfa.dfaserver.invest.dto.error.ErrorDto;
import com.dfa.dfaserver.invest.dto.order.InputMarketOrderDto;
import com.dfa.dfaserver.invest.dto.order.MarketOrderDto;
import com.dfa.dfaserver.invest.exception.NotFoundException;
import com.dfa.dfaserver.invest.mapper.order.MarketOrderMapper;
import com.dfa.dfaserver.invest.service.order.MarketOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders/market")
@RequiredArgsConstructor
@Validated
@Tag(name = "Market orders", description = "Create, retrieve, update and delete market orders")
public class MarketOrderController {
    private final MarketOrderService marketOrderService;
    private final MarketOrderMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create market order",
            description = "Create market order and automatically send it to the execution queue",
            responses = {@ApiResponse(description = "Created", responseCode = "201", useReturnTypeSchema = true),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDto.class))}),
                    @ApiResponse(description = "Not authorized", responseCode = "401", content = {@Content(schema = @Schema(implementation = Void.class))})})
    public MarketOrderDto createMarketOrder(@Valid @RequestBody InputMarketOrderDto dto) {
        MarketOrder order = mapper.fromDto(dto);
        order = marketOrderService.save(order);
        return mapper.toDto(order);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get market order",
            description = "Get market order by ID",
            responses = {@ApiResponse(description = "OK", responseCode = "200", useReturnTypeSchema = true),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDto.class))}),
                    @ApiResponse(description = "Not authorized", responseCode = "401", content = {@Content(schema = @Schema(implementation = Void.class))}),
                    @ApiResponse(description = "Not found", responseCode = "404", content = {@Content(schema = @Schema(implementation = Void.class))})})
    public MarketOrderDto getMarketOrder(@PathVariable UUID id) {
        MarketOrder order = marketOrderService.findById(id)
                .orElseThrow(() -> new NotFoundException("Market order with id '%s' could not be found", id));

        return mapper.toDto(order);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit market order",
            description = "Edit market order by ID",
            responses = {@ApiResponse(description = "OK", responseCode = "200", useReturnTypeSchema = true),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDto.class))}),
                    @ApiResponse(description = "Not authorized", responseCode = "401", content = {@Content(schema = @Schema(implementation = Void.class))}),
                    @ApiResponse(description = "Not found", responseCode = "404", content = {@Content(schema = @Schema(implementation = Void.class))})})
    public MarketOrderDto editMarketOrder(@PathVariable UUID id,
                                        @Valid @org.springframework.web.bind.annotation.RequestBody InputMarketOrderDto dto) {
        MarketOrder order = mapper.fromDto(dto);
        order = marketOrderService.updateById(id, order);
        return mapper.toDto(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete market order",
            description = "Delete market order by ID",
            responses = {@ApiResponse(description = "OK", responseCode = "200", useReturnTypeSchema = true),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDto.class))}),
                    @ApiResponse(description = "Not authorized", responseCode = "401", content = {@Content(schema = @Schema(implementation = Void.class))}),
                    @ApiResponse(description = "Not found", responseCode = "404", content = {@Content(schema = @Schema(implementation = Void.class))})})
    public void deleteMarketOrder(@PathVariable UUID id) {
        marketOrderService.deleteById(id);
    }

    @GetMapping
    @Operation(summary = "Get all market orders",
            description = "Get all market orders",
            responses = {@ApiResponse(description = "OK", responseCode = "200", useReturnTypeSchema = true),
                    @ApiResponse(description = "Not authorized", responseCode = "401", content = {@Content(schema = @Schema(implementation = Void.class))})})

    public List<MarketOrderDto> getAllMarketOrders() {
        return marketOrderService.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

}
