package com.dfa.dfaserver.invest.controller.order;

import com.dfa.dfaserver.invest.domain.order.MarketOrder;
import com.dfa.dfaserver.invest.dto.order.InputMarketOrderDto;
import com.dfa.dfaserver.invest.dto.order.MarketOrderDto;
import com.dfa.dfaserver.invest.exception.NotFoundException;
import com.dfa.dfaserver.invest.mapper.order.MarketOrderMapper;
import com.dfa.dfaserver.invest.service.order.MarketOrderService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
    public MarketOrderDto createMarketOrder(@Valid @RequestBody InputMarketOrderDto dto) {
        MarketOrder order = mapper.fromDto(dto);
        order = marketOrderService.save(order);
        return mapper.toDto(order);
    }

    @GetMapping("/{id}")
    public MarketOrderDto getMarketOrder(@PathVariable UUID id) {
        MarketOrder order = marketOrderService.findById(id)
                .orElseThrow(() -> new NotFoundException("Market order with id '%s' could not be found", id));

        return mapper.toDto(order);
    }

    @PutMapping("/{id}")
    public MarketOrderDto editMarketOrder(@PathVariable UUID id,
                                        @Valid @org.springframework.web.bind.annotation.RequestBody InputMarketOrderDto dto) {
        MarketOrder order = mapper.fromDto(dto);
        order = marketOrderService.updateById(id, order);
        return mapper.toDto(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMarketOrder(@PathVariable UUID id) {
        marketOrderService.deleteById(id);
    }

    @GetMapping
    public List<MarketOrderDto> getAllMarketOrders() {
        return marketOrderService.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

}
