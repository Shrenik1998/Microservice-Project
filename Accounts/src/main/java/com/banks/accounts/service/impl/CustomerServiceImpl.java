package com.banks.accounts.service.impl;

import com.banks.accounts.Exceptions.CustomerAlreadyExists;
import com.banks.accounts.Exceptions.ResourceNotFound;
import com.banks.accounts.dtos.CustomerDTO;
import com.banks.accounts.entity.Customer;
import com.banks.accounts.mapper.CustomerMapper;
import com.banks.accounts.repository.CustomerRepository;
import com.banks.accounts.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private CustomerRepository customerRepository;

    @Override
    public void createCustomer(CustomerDTO customerDTO) throws CustomerAlreadyExists {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO,new Customer());
        Optional<Customer> customerOptional = customerRepository.findByEmail(customer.getEmail());
        if(customerOptional.isPresent()) {
            throw new CustomerAlreadyExists("Customer Already Exists");
        }
//        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonomys");
        customerRepository.save(customer);
    }

    @Override
    public CustomerDTO getCustomerById(long id) throws ResourceNotFound {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Customer not found with id: " + id));
        return CustomerMapper.mapToCustomerDto(customer, new CustomerDTO());
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return List.of();
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO, Long id) throws ResourceNotFound {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(!optionalCustomer.isPresent()) {
            throw new ResourceNotFound("Customer not found with id: " + id);
        }
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, optionalCustomer.get());
//        customer.setUpdatedAt(LocalDateTime.now());
        customer.setUpdatedBy("Anonomys");
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(!optionalCustomer.isPresent()) {
            throw new ResourceNotFound("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }
}
