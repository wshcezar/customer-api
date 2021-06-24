package com.customer.api.controller.data.response;

import com.customer.api.enums.CustomerStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"customerId", "name", "email", "cpfCnpj", "birthDate", "age", "status"})
public class CustomerResponse implements Serializable {

    @JsonProperty("customer_id")
    private Long customerId;

    private String name;

    private String email;

    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;

    @JsonProperty("birth_date")
    private LocalDate birthDate;

    private int age;

    private CustomerStatus status;
}