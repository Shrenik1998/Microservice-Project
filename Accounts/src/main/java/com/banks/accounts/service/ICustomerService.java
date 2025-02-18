package com.banks.accounts.service;

import com.banks.accounts.Exceptions.CustomerAlreadyExists;
import com.banks.accounts.Exceptions.ResourceNotFound;
import com.banks.accounts.dtos.CustomerDTO;
import com.banks.accounts.entity.Customer;

import java.util.List;

public interface ICustomerService {
    public void createCustomer(CustomerDTO customerDTO) throws CustomerAlreadyExists;
    CustomerDTO getCustomerById(long id) throws ResourceNotFound;
    public List<CustomerDTO> getAllCustomers();
    public void updateCustomer(CustomerDTO customerDTO,Long id) throws ResourceNotFound;
    public void deleteCustomer(long id);
}
