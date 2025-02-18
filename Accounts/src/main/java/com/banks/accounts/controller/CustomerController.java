package com.banks.accounts.controller;

import com.banks.accounts.Exceptions.CustomerAlreadyExists;
import com.banks.accounts.Exceptions.ResourceNotFound;
import com.banks.accounts.dtos.CustomerDTO;
import com.banks.accounts.dtos.ResponseDTO;
import com.banks.accounts.service.ICustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
@Validated
public class CustomerController {

    private ICustomerService customerService;

    @PostMapping()
    public ResponseEntity<ResponseDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws CustomerAlreadyExists {
        customerService.createCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO<>(HttpStatus.CREATED,
                        "Customer created successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getCustomerBuId(@PathVariable long id) throws ResourceNotFound {
        CustomerDTO customerDTO = customerService.getCustomerById(id);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new ResponseDTO<>(HttpStatus.FOUND,
                        "Customer found",customerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateCustomer(@PathVariable long id,@Valid @RequestBody CustomerDTO customerDTO) throws ResourceNotFound {
        customerService.updateCustomer(customerDTO, id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO<>(HttpStatus.OK,
                        "Customer details updated successfully",customerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteCustomer(@PathVariable long id) throws ResourceNotFound {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO<>(HttpStatus.OK,
                        "Customer deleted successfully"));
    }
}
