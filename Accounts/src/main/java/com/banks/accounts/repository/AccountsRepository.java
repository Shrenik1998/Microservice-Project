package com.banks.accounts.repository;

import com.banks.accounts.entity.Accounts;
import com.banks.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {
    Optional<List<Accounts>> findByCustomer(Customer customer);
    Optional<Accounts> findByAccountNumber(long accNo);
}
