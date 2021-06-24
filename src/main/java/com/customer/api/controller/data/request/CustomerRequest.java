package com.customer.api.controller.data.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest implements Serializable {

    @NotNull(message = "{name.notnull}")
    @NotEmpty(message = "{name.notempty}")
    private String name;

    @Email(message = "{email.validation}")
    @NotEmpty(message = "{email.notempty}")
    private String email;

    @NotNull(message = "{cpfcnpj.notnull}")
    @NotEmpty(message = "{cpfcnpj.notempty}")
    @Size(max = 14, message = "{cpfcnpj.size}")
    @Pattern(regexp = "[0-9]+", message = "{cpfcnpj.isnumberonly}")
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;

    @NotNull(message = "{birthdate.notnull}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("birth_date")
    private LocalDate birthDate;
}