package com.healthdomian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="INSURANCE_PLAN")
@Data
public class InsurancePlan {

	@Id
	@Column(name="PLAN_ID")
	private Integer planId;
	
	@Column(name="PLAN_NAME")
	private String planName;
	
	@Column(name="HOLDER_NAME")
	private String holderName;
	
	@Column(name="PLAN_STATUS")
	private String planStatus;
	
	@Column(name="BENEFIT_AMT")
	private String benefitAmount;
	
}
