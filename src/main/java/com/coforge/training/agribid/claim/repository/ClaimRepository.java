package com.coforge.training.agribid.claim.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.coforge.training.agribid.claim.model.Claim;


public interface ClaimRepository extends JpaRepository<Claim, Long> {


}

