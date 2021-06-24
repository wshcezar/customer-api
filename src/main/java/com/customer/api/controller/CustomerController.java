package com.customer.api.controller;

import com.customer.api.controller.data.request.CustomerPartRequest;
import com.customer.api.controller.data.request.CustomerRequest;
import com.customer.api.controller.data.response.CustomerEnvelope;
import com.customer.api.controller.data.response.CustomerResponse;
import com.customer.api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomerEndpoint {

    private final CustomerService service;
    private final PagedResourcesAssembler<CustomerResponse> assembler;

    @Override
    public ResponseEntity<CustomerEnvelope> create(final CustomerRequest request) {

        var response = service.createCustomer(request);

        return ResponseEntity.ok(CustomerEnvelope.builder().data(response).build());
    }

    @Override
    public ResponseEntity<?> findAll(Pageable page) {

        var response = service.getAllCustomers(page);

        return new ResponseEntity<>(assembler.toModel(response), HttpStatus.OK);
    }

    @Override
    public CustomerEnvelope findById(final Long customerId) {

        var response = service.getByIdCustomer(customerId);

        return CustomerEnvelope.builder().data(response).build();
    }

    @Override
    public CustomerEnvelope update(final Long customerId, final CustomerRequest request) {

        var response = service.updateCustomer(customerId, request);

        return CustomerEnvelope.builder().data(response).build();
    }

    @Override
    public CustomerEnvelope updatePart(final Long customerId, final CustomerPartRequest request) {

        var response = service.updatePartCustomer(customerId, request);

        return CustomerEnvelope.builder().data(response).build();
    }
}