package com.customer.api.entity;

import com.customer.api.enums.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long id;

    @Column(name = "name_customer", nullable = false)
    private String name;

    @Column(name = "email_customer", nullable = false)
    private String email;

    @Column(name = "cpf_cnpj_customer", nullable = false)
    private String cpfCnpj;

    @Column(name = "birth_date_customer", nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_customer", nullable = false)
    private CustomerStatus status;

    @PrePersist
    void createCustomer() {
        status = CustomerStatus.ACTIVE;
    }
}