package com.customer.api.converter;

import com.customer.api.controller.data.request.CustomerPartRequest;
import com.customer.api.controller.data.request.CustomerRequest;
import com.customer.api.controller.data.response.CustomerResponse;
import com.customer.api.entity.CustomerEntity;
import com.customer.api.service.BirthDateService;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Component
@Generated
@RequiredArgsConstructor
public class CustomerConverter {

    private final BirthDateService service;

    public CustomerEntity toCustomerEntity(final CustomerRequest request) {
        return CustomerEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .cpfCnpj(request.getCpfCnpj())
                .birthDate(request.getBirthDate())
                .build();
    }

    public CustomerEntity toCustomerEntityUpdate(final CustomerEntity entity, final CustomerRequest request) {
        return CustomerEntity.builder()
                .id(entity.getId())
                .name(request.getName())
                .email(request.getEmail())
                .cpfCnpj(request.getCpfCnpj())
                .birthDate(request.getBirthDate())
                .status(entity.getStatus())
                .build();
    }

    public CustomerEntity toCustomerEntityPartUpdate(final CustomerEntity entity, final CustomerPartRequest request) {

        entity.setName(nonNull(request.getName()) ? request.getName() : entity.getName());
        entity.setEmail(nonNull(request.getEmail()) ? request.getEmail() : entity.getEmail());
        entity.setStatus(nonNull(request.getStatus()) ? request.getStatus() : entity.getStatus());

        return entity;
    }

    public CustomerResponse toCustomerResponse(final CustomerEntity entity) {
        return CustomerResponse.builder()
                .customerId(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .cpfCnpj(entity.getCpfCnpj())
                .birthDate(entity.getBirthDate())
                .age(service.getAge(entity.getBirthDate()))
                .status(entity.getStatus())
                .build();
    }

    public List<CustomerResponse> toCustomerResponseList(final List<CustomerEntity> entities) {
        return entities.stream().map(this::toCustomerResponse).collect(Collectors.toList());
    }
}