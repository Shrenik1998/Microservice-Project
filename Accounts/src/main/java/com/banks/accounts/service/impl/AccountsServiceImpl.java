package com.banks.accounts.service.impl;

import com.banks.accounts.Exceptions.ResourceNotFound;
import com.banks.accounts.dtos.AccountsDTO;
import com.banks.accounts.entity.Accounts;
import com.banks.accounts.entity.Customer;
import com.banks.accounts.mapper.AccountsMapper;
import com.banks.accounts.repository.AccountsRepository;
import com.banks.accounts.repository.CustomerRepository;
import com.banks.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(AccountsDTO accountsDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(accountsDTO.getCustomerId());
        if (!optionalCustomer.isPresent()){
            throw new ResourceNotFound("Customer not found with id: " + accountsDTO.getCustomerId());
        }

        Accounts accounts = AccountsMapper.mapToAccounts(accountsDTO,new Accounts());
        accounts.setCustomer(optionalCustomer.get());
        accountsRepository.save(accounts);
    }

    @Override
    public List<AccountsDTO> getAccountsDetailsByCustomerId(long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFound("Customer not found with id: " + customerId));

        List<Accounts> accountsList = accountsRepository.findByCustomer(customer)
                .orElseThrow(() -> new ResourceNotFound("No accounts found for customer with id: " + customerId));

        return accountsList.stream()
                .map(account -> AccountsMapper.mapToAccountsDto(account, new AccountsDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public void updateAccountByCustomerIdAndAccNumber(long customerId, long accNumber, AccountsDTO accountsDTO) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFound("Customer not found with id: " + customerId));

        Accounts accounts = accountsRepository.findByAccountNumber(accNumber)
                .orElseThrow(()->new ResourceNotFound("Account with account number:"+accNumber+
                        " not found for customer with customerId:"+customerId));

        accounts.setAccountType(accountsDTO.getAccountType());
        accounts.setBranchAddress(accountsDTO.getBranchAddress());
        accountsRepository.save(accounts);
    }

    @Override
    public void deleteAccountByCustomerIdAndAccNumber(long customerId, long accNumber) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFound("Customer not found with id: " + customerId));

        Accounts accounts = accountsRepository.findByAccountNumber(accNumber)
                .orElseThrow(()->new ResourceNotFound("Account with account number:"+accNumber+
                        " not found for customer with customerId:"+customerId));

        accountsRepository.deleteById(accounts.getId());
    }

}
