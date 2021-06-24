package com.customer.api.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String message;
	private String details;
	private LocalDateTime timestamp;
}