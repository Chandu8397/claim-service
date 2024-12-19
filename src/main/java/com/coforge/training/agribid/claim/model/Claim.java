package com.coforge.training.agribid.claim.model;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Claim {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		//p-key
	private Long id;
	
	
	@Column(nullable = false)
	private String insuranceCompany;
	
	@Column(nullable = false)
	private Double sumInsured;
	
	@Column(nullable = false)
	private String causeOfLoss;
	
	@Column(nullable = false)
	private Date dateOfLoss;
	
	
	@Lob
    @JsonIgnore
    private byte[] supportingDocuments;
	

}

