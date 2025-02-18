package com.banks.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accounts")
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Accounts extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @Column(name="account_number")
    private Long accountNumber;

    @Column(name="account_type")
    private String accountType;

    @Column(name="branch_address")
    private String branchAddress;
}
