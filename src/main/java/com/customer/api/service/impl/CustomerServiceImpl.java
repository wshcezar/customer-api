package com.customer.api.service.impl;

import com.customer.api.controller.data.request.CustomerPartRequest;
import com.customer.api.controller.data.request.CustomerRequest;
import com.customer.api.controller.data.response.CustomerResponse;
import com.customer.api.converter.CustomerConverter;
import com.customer.api.entity.CustomerEntity;
import com.customer.api.exception.ResourceNotFoundException;
import com.customer.api.exception.ValidatorCustomerException;
import com.customer.api.repository.CustomerRepository;
import com.customer.api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private static final String VALIDATION_01 = "VALIDATION-01";
    private static final String CUSTOMER_DATA_01 = "CUSTOMER-DATA-01";
    private static final String CUSTOMER_DATA_02 = "CUSTOMER-DATA-02";
    private static final String CUSTOMER_DATA_03 = "CUSTOMER-DATA-03";
    private static final String CUSTOMER_DATA_04 = "CUSTOMER-DATA-04";

    private final CustomerRepository repository;
    private final CustomerConverter converter;

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {

        if (repository.findByCpfCnpj(request.getCpfCnpj()).isPresent()) {
            throw new ValidatorCustomerException(VALIDATION_01, request.getCpfCnpj());
        }

        var entity = repository.saveAndFlush(converter.toCustomerEntity(request));

        if (isNull(entity))
            throw new ResourceNotFoundException(CUSTOMER_DATA_01);

        return converter.toCustomerResponse(entity);
    }

    @Override
    public Page<CustomerResponse> getAllCustomers(Pageable page) {

        var pages = repository.findAll(page);

        if (isNull(pages))
            throw new ResourceNotFoundException(CUSTOMER_DATA_02);

        return pages.map(o -> converter.toCustomerResponse(o));
    }

    @Override
    public CustomerResponse getByIdCustomer(Long customerId) {

        var entity = getById(customerId);

        return converter.toCustomerResponse(entity);
    }

    @Override
    public CustomerResponse updateCustomer(Long customerId, CustomerRequest request) {

        var customerEntity = converter.toCustomerEntityUpdate(this.getById(customerId), request);

        return update(customerEntity, request.getCpfCnpj());
    }

    @Override
    public CustomerResponse updatePartCustomer(Long customerId, CustomerPartRequest request) {

        var customerEntity = this.getById(customerId);

        return update(converter.toCustomerEntityPartUpdate(customerEntity, request), request.getName());
    }

    private CustomerResponse update(CustomerEntity customerEntity, String attribute) {

        var entity = repository.saveAndFlush(customerEntity);

        if (isNull(entity))
            throw new ResourceNotFoundException(CUSTOMER_DATA_04, attribute);

        return converter.toCustomerResponse(entity);
    }

    private CustomerEntity getById(Long customerId) {
        return repository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException(CUSTOMER_DATA_03, customerId));
    }
}