package com.banks.accounts.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    @Column(updatable = false,name = "created_at")
    private LocalDateTime createdAt;

    @Column(updatable = false, name = "created_by")
    private String createdBy;

    @UpdateTimestamp
    @Column(insertable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(insertable = false, name = "updatedBy")
    private String updatedBy;
}
