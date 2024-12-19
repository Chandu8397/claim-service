package com.coforge.training.agribid.claim.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "insurance-service")
public interface InsuranceClient {
	

	@GetMapping("/agribid/insurance/insurance/{id}")
	public ResponseEntity<?> getInsuranceById(@PathVariable Long id);

}
