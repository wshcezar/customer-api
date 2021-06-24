package com.customer.api.controller.data.request;

import com.customer.api.enums.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPartRequest implements Serializable {

    private String name;
    private String email;
    private CustomerStatus status;
}