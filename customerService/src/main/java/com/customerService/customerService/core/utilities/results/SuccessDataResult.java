package com.customerService.customerService.core.utilities.results;

public class SuccessDataResult<T> extends DataResult<T>{

	public SuccessDataResult(boolean success, T data) {
		super(success, data);
	}

	
}
