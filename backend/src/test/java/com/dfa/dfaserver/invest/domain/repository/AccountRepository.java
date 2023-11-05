package com.dfa.dfaserver.invest.domain.repository;

import com.dfa.dfaserver.invest.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
}
