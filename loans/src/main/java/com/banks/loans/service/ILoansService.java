package com.banks.loans.service;

import com.banks.loans.dtos.LoansDTO;
import com.banks.loans.entity.Loans;

public interface ILoansService {    /**
 *
 * @param mobileNumber - Mobile Number of the Customer
 */
void createLoan(String mobileNumber);

    LoansDTO fetchLoan(String mobileNumber);

    void createLoan(LoansDTO loansDTO);

    boolean updateLoan(LoansDTO loansDto);

    boolean deleteLoan(String mobileNumber);

}
