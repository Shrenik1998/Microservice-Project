package com.banks.accounts.service;

import com.banks.accounts.dtos.AccountsDTO;

import java.util.List;

public interface IAccountsService {
    public void createAccount(AccountsDTO accountsDTO);
    public List<AccountsDTO> getAccountsDetailsByCustomerId(long customerId);
    public void updateAccountByCustomerIdAndAccNumber(long customerId,long accNumber,AccountsDTO accountsDTO);
    public void deleteAccountByCustomerIdAndAccNumber(long customerId,long accNumber);
}
