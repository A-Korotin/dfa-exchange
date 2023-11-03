package com.dfa.dfaserver.domain.repository;

import com.dfa.dfaserver.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
}
