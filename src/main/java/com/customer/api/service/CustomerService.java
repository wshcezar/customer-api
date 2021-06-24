package com.customer.api.service;

import com.customer.api.controller.data.request.CustomerPartRequest;
import com.customer.api.controller.data.request.CustomerRequest;
import com.customer.api.controller.data.response.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerResponse createCustomer(final CustomerRequest request);

    Page<CustomerResponse> getAllCustomers(Pageable page);

    CustomerResponse getByIdCustomer(Long customerId);

    CustomerResponse updateCustomer(Long customerId, CustomerRequest request);

    CustomerResponse updatePartCustomer(Long customerId, CustomerPartRequest request);
}