package com.coforge.training.agribid.claim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coforge.training.agribid.claim.model.Claim;
import com.coforge.training.agribid.claim.repository.ClaimRepository;


@Service
public class ClaimService {
	
	@Autowired
	private ClaimRepository crepo;
	
	public Claim saveClaim(Claim c) {
    	return crepo.save(c);
    }
	
	
	
	

}

