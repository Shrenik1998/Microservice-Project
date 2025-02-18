package com.banks.accounts.controller;

import com.banks.accounts.dtos.AccountsDTO;
import com.banks.accounts.dtos.ResponseDTO;
import com.banks.accounts.service.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService accountsService;

    @PostMapping()
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody AccountsDTO accountsDTO) {
        accountsService.createAccount(accountsDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(HttpStatus.CREATED,"accoount created"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getAccountsByCustomerId(@PathVariable long id){
        List<AccountsDTO> accountsDTOList = accountsService.getAccountsDetailsByCustomerId(id);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new ResponseDTO(HttpStatus.FOUND,"accounts found",accountsDTOList));
    }

    @PutMapping()
    public ResponseEntity<ResponseDTO> updateAccountByCustomerIdAndAccNumber(@RequestParam long customerId,
                                                                             @RequestParam @Digits(integer = 10, fraction = 0, message = "Account number must be a 10-digit number") long accNo,
                                                                             @Valid @RequestBody AccountsDTO accountsDTO) {
        accountsService.updateAccountByCustomerIdAndAccNumber(customerId,accNo,accountsDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(HttpStatus.OK,"accoount details updated"));
    }

    @DeleteMapping()
    public ResponseEntity<ResponseDTO> deleteAccountByCustomerIdAndAccNumber(@RequestParam long customerId,
                                                                             @RequestParam @Digits(integer = 10, fraction = 0, message = "Account number must be a 10-digit number") long accNo) {
        accountsService.deleteAccountByCustomerIdAndAccNumber(customerId,accNo);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(HttpStatus.OK,"accoount deleted successfully"));
    }

}
