package com.banks.accounts.mapper;

import com.banks.accounts.dtos.AccountsDTO;
import com.banks.accounts.entity.Accounts;

public class AccountsMapper {
    public static AccountsDTO mapToAccountsDto(Accounts accounts, AccountsDTO accountsDto) {
        accountsDto.setCustomerId(accounts.getCustomer().getId());
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Accounts mapToAccounts(AccountsDTO accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
