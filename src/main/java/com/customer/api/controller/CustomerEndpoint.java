package com.customer.api.controller;

import com.customer.api.controller.data.request.CustomerPartRequest;
import com.customer.api.controller.data.request.CustomerRequest;
import com.customer.api.controller.data.response.CustomerEnvelope;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api(tags = "Customers", value = "The Customers API")
@RequestMapping("/api/customers")
public interface CustomerEndpoint {

    @ApiOperation(value = "Create Customer", response = CustomerEnvelope.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create Success Customer"),
            @ApiResponse(code = 400, message = "Customer invalid data"),
            @ApiResponse(code = 500, message = "Customer error into server")
    })
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    ResponseEntity<CustomerEnvelope> create(
            @ApiParam(value = "Payload Customer", required = true)
            @Valid @RequestBody final CustomerRequest request);

    @ApiOperation(value = "FindAll Customers", notes = "Endpoint with paging and HATEOAS.", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "FindAll Customers"),
            @ApiResponse(code = 400, message = "Customer invalid data"),
            @ApiResponse(code = 500, message = "Customer error into server")
    })
    @GetMapping
    ResponseEntity<?> findAll(
            @ApiParam("Pageable Customer")
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC, page = 0, size = 10) final Pageable page);

    @ApiOperation(value = "FindById Customer",  response = CustomerEnvelope.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Find Unique Customer"),
            @ApiResponse(code = 400, message = "Customer invalid data"),
            @ApiResponse(code = 500, message = "Customer error into server")
    })
    @GetMapping("/{customer_id}")
    CustomerEnvelope findById(
            @ApiParam(value = "Identifier Customer", required = true)
            @PathVariable("customer_id") @NotNull(message = "{customerid.notnull}") final Long customerId);

    @ApiOperation(value = "Update Customer",  response = CustomerEnvelope.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update Customer"),
            @ApiResponse(code = 400, message = "Customer invalid data"),
            @ApiResponse(code = 500, message = "Customer error into server")
    })
    @PutMapping("/{customer_id}")
    CustomerEnvelope update(
            @ApiParam(value = "Identifier Customer", required = true)
            @PathVariable("customer_id") @NotNull(message = "{customerid.notnull}") final Long customerId,

            @ApiParam(value = "Payload Customer", required = true)
            @Valid @RequestBody final CustomerRequest request);

    @ApiOperation(value = "Update Part Customer",  response = CustomerEnvelope.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update Part Customer"),
            @ApiResponse(code = 400, message = "Customer invalid data"),
            @ApiResponse(code = 500, message = "Customer error into server")
    })
    @PatchMapping("/{customer_id}")
    CustomerEnvelope updatePart(
            @ApiParam(value = "Identifier Customer", required = true)
            @PathVariable("customer_id") @Valid @NotNull(message = "{customerid.notnull}") final Long customerId,

            @ApiParam(value = "Payload Part Customer", required = true)
            @Valid @RequestBody final @NotNull CustomerPartRequest request);
}