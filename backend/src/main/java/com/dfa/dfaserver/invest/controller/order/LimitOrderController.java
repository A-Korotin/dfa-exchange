package com.dfa.dfaserver.invest.controller.order;

import com.dfa.dfaserver.invest.domain.order.LimitOrder;
import com.dfa.dfaserver.invest.dto.order.InputLimitOrderDto;
import com.dfa.dfaserver.invest.dto.order.LimitOrderDto;
import com.dfa.dfaserver.invest.exception.NotFoundException;
import com.dfa.dfaserver.invest.mapper.order.LimitOrderMapper;
import com.dfa.dfaserver.invest.service.order.LimitOrderService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public LimitOrderDto createLimitOrder(@Valid @RequestBody @Schema() InputLimitOrderDto dto) {
        LimitOrder order = mapper.fromDto(dto);
        order = limitOrderService.save(order);
        return mapper.toDto(order);
    }

    @GetMapping("/{id}")
    public LimitOrderDto getLimitOrder(@PathVariable UUID id) {
        LimitOrder order = limitOrderService.findById(id)
                .orElseThrow(() -> new NotFoundException("Limit order with id '%s' could not be found", id));

        return mapper.toDto(order);
    }

    @PutMapping("/{id}")
    public LimitOrderDto editLimitOrder(@PathVariable UUID id,
                                        @Valid @RequestBody InputLimitOrderDto dto) {
        LimitOrder order = mapper.fromDto(dto);
        order = limitOrderService.updateById(id, order);
        return mapper.toDto(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLimitOrder(@PathVariable UUID id) {
        limitOrderService.deleteById(id);
    }

    @GetMapping
    public List<LimitOrderDto> getAllLimitOrders() {
        return limitOrderService.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }
}
