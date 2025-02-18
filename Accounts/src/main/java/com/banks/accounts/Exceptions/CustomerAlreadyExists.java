package com.banks.accounts.Exceptions;

public class CustomerAlreadyExists extends RuntimeException {
    public CustomerAlreadyExists(String customerAlreadyExists) {
        super(customerAlreadyExists);
    }
}
