package com.algaworks.algamoney.api.validation.model;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationErrorResponse {
	private List<String> errors;
	
	private ValidationErrorResponse(List<String> errors) {
		this.errors = errors;
	}
	
	public static ValidationErrorResponse of(String error) {
		return new ValidationErrorResponse(Arrays.asList(error));
	}
	
	public static ValidationErrorResponse of(List<String> errors) {
		return new ValidationErrorResponse(errors);
	}
}
