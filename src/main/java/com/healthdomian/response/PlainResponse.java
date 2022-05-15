package com.healthdomian.response;

import lombok.Data;

@Data
public class PlainResponse {

	private Integer planId;
	private String planName;
	private String holderName;
	private String planStatus;
	private String benefitAmount;
}
