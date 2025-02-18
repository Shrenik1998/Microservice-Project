package com.banks.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Customer extends BaseEntity{

    private String name;

    private String email;

    @Column(name="mobile_number")
    private String mobileNumber;
}
