package com.banks.loans.controller;

import com.banks.loans.dtos.LoansDTO;
import com.banks.loans.dtos.ResponseDTO;
import com.banks.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "CRUD REST APIs for Loans in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE loan details"
)
@RestController
@RequestMapping(path = "/api/loans", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoansController {

    private ILoansService loansService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createLoan(@RequestParam
                                                  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                  String mobileNumber) {
        loansService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(HttpStatus.CREATED,"loan created successfully"));
    }

    @GetMapping("/fetch")
    public ResponseEntity<ResponseDTO> getLoan(@RequestParam
                                                  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                  String mobileNumber) {
        LoansDTO loansDTO = loansService.fetchLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(HttpStatus.CREATED,"loan details fetched successfully",loansDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> getLoan(@Valid @RequestBody LoansDTO loansDTO) {
        boolean isUpdated = loansService.updateLoan(loansDTO);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(HttpStatus.OK,"loan details updated successfully"));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDTO(HttpStatus.CREATED,"falied to update loan details"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteLoan(@RequestParam
                                               @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                               String mobileNumber) {
        boolean isDeleted = loansService.deleteLoan(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(HttpStatus.OK,"loan deleted successfully"));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDTO(HttpStatus.CREATED,"falied to delete loan"));
    }



}
