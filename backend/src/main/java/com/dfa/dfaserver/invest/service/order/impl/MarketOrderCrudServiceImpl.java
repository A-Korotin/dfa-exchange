package com.dfa.dfaserver.invest.service.order.impl;

import com.dfa.dfaserver.invest.domain.order.MarketOrder;
import com.dfa.dfaserver.invest.exception.NotFoundException;
import com.dfa.dfaserver.invest.repository.order.MarketOrderRepository;
import com.dfa.dfaserver.invest.service.order.MarketOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MarketOrderCrudServiceImpl implements MarketOrderService {

    private final MarketOrderRepository marketOrderRepository;

    @Override
    public Optional<MarketOrder> findById(UUID id) {
        return marketOrderRepository.findById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return marketOrderRepository.existsById(id);
    }

    @Override
    public MarketOrder save(MarketOrder entity) {
        return marketOrderRepository.save(entity);
    }

    @Override
    public MarketOrder updateById(UUID id, MarketOrder order) {
        order.setId(id);
        return marketOrderRepository.save(order);
    }

    @Override
    public void deleteById(UUID id) {
        if (!existsById(id)) {
            throw new NotFoundException("Market order with id '%s' could not be found", id);
        }
        marketOrderRepository.deleteById(id);
    }

    @Override
    public List<MarketOrder> findAll() {
        return marketOrderRepository.findAll();
    }
}
