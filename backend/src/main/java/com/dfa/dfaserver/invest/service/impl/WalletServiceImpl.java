package com.dfa.dfaserver.invest.service.impl;


import com.dfa.dfaserver.invest.domain.Wallet;
import com.dfa.dfaserver.invest.repository.WalletRepository;
import com.dfa.dfaserver.invest.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    public Optional<Wallet> findById(UUID uuid) {
        return walletRepository.findById(uuid);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return walletRepository.existsById(uuid);
    }

    @Override
    public Wallet save(Wallet entity) {
        return walletRepository.save(entity);
    }

    @Override
    public Wallet updateById(UUID uuid, Wallet entity) {
        entity.setId(uuid);
        return walletRepository.save(entity);
    }

    @Override
    public void deleteById(UUID uuid) {
        walletRepository.deleteById(uuid);
    }

    @Override
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }
}
