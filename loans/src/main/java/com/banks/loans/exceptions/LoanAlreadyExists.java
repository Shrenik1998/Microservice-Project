package com.banks.loans.exceptions;

public class LoanAlreadyExists extends RuntimeException {
    public LoanAlreadyExists(String msg) {
        super(msg);
    }
}
