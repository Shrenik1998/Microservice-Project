package com.banks.loans.service.impl;

import com.banks.loans.constants.LoansConstants;
import com.banks.loans.dtos.LoansDTO;
import com.banks.loans.entity.Loans;
import com.banks.loans.exceptions.LoanAlreadyExists;
import com.banks.loans.exceptions.ResourceNotFound;
import com.banks.loans.mapper.LoansMapper;
import com.banks.loans.repository.LoansRepository;
import com.banks.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {
    private LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loans = loansRepository.findByMobileNumber(mobileNumber);
        if (loans.isPresent()) {
            throw new LoanAlreadyExists("Loan already exists");
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    @Override
    public LoansDTO fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFound("Loan does not exist for the mobile : " + mobileNumber)
        );
        return LoansMapper.mapToLoansDto(loans, new LoansDTO());
    }

    @Override
    public void createLoan(LoansDTO loansDTO) {

    }

    @Override
    public boolean updateLoan(LoansDTO loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFound("No loans exist for loan number : "+loansDto.getLoanNumber()));
        LoansMapper.mapToLoans(loansDto, loans);
        loansRepository.save(loans);
        return  true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFound("No loans exist for loan number : "+mobileNumber)
        );
        loansRepository.deleteById((int)loans.getId());
        return true;
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }
}
