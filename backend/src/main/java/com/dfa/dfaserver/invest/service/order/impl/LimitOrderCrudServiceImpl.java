package com.dfa.dfaserver.invest.service.order.impl;

import com.dfa.dfaserver.invest.domain.order.LimitOrder;
import com.dfa.dfaserver.invest.exception.NotFoundException;
import com.dfa.dfaserver.invest.repository.order.LimitOrderRepository;
import com.dfa.dfaserver.invest.service.order.LimitOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LimitOrderCrudServiceImpl implements LimitOrderService {
    private final LimitOrderRepository limitOrderRepository;


    @Override
    public Optional<LimitOrder> findById(UUID id) {
        return limitOrderRepository.findById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return limitOrderRepository.existsById(id);
    }

    @Override
    public LimitOrder save(LimitOrder order) {
        return limitOrderRepository.save(order);
    }

    @Override
    public LimitOrder updateById(UUID id, LimitOrder order) {
        order.setId(id);
        return limitOrderRepository.save(order);
    }

    @Override
    public void deleteById(UUID id) {
        if (!existsById(id)) {
            throw new NotFoundException("Limit order with id '%s' could not be found", id);
        }
        limitOrderRepository.deleteById(id);
    }

    @Override
    public List<LimitOrder> findAll() {
        return limitOrderRepository.findAll();
    }
}
