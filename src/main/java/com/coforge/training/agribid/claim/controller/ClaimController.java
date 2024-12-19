package com.coforge.training.agribid.claim.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coforge.training.agribid.claim.model.Claim;
import com.coforge.training.agribid.claim.service.ClaimService;
import com.coforge.training.agribid.claim.service.InsuranceClient;
import com.coforge.training.agribid.insurance.model.Insurance;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/claim-service/api")
@RestController
public class ClaimController {
	
	 @Autowired
     private ClaimService claimService;
	 
	 @Autowired
	 private InsuranceClient insuranceClient;
	
	    @PostMapping("/claim")
	    public ResponseEntity<Claim> saveClaim(
	            @RequestPart("claim") String claimJson,
	            @RequestPart("supportingDocuments") MultipartFile supportingDocuments) {
		 
	        try {
	        	
	            ObjectMapper objectMapper = new ObjectMapper();
	            Claim claim = objectMapper.readValue(claimJson, Claim.class);

	            claim.setSupportingDocuments(supportingDocuments.getBytes());

	            Claim savedClaim = claimService.saveClaim(claim);
	            return ResponseEntity.status(HttpStatus.CREATED).body(savedClaim);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }

	    
	    //using feign client fetching data from Insurance Service
	    @GetMapping("/insurance/{id}")
		public ResponseEntity<?> getInsurance(@PathVariable Long id) {
		    try {
		        ResponseEntity<?> insurance = insuranceClient.getInsuranceById(id);
		        if (insurance != null) {
		            return ResponseEntity.ok(insurance.getBody());
		        } else {
		            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Insurance not found.");
		        }
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching the insurance." +e);
		    }
		}

}
