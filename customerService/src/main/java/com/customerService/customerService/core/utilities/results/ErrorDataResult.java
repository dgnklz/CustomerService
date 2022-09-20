package com.customerService.customerService.core.utilities.results;

public class ErrorDataResult<T> extends DataResult<T>{

	public ErrorDataResult(boolean success, String message) {
		super(success, message);
	}
	
}
