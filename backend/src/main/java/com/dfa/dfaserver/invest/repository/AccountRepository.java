package com.dfa.dfaserver.invest.repository;

import com.dfa.dfaserver.invest.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
