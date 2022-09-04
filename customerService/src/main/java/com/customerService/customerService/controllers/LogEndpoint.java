package com.customerService.customerService.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogEndpoint {
	
	
	
	@GetMapping
	public String getDetails() {
		log.debug("getDetails method has started");
		return internalLogDetail();
	}

	private String internalLogDetail() {
		try {
			log.debug("internalLogDetail method has started");
			Thread.sleep(1000);
			return "API Message";
		}catch(InterruptedException e){
			log.error("Error : {}", e);
		}
		return "";
	}
}
