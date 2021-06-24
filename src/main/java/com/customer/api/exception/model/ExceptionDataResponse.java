package com.customer.api.exception.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ExceptionDataResponse {

    private ExceptionResponse data;
}