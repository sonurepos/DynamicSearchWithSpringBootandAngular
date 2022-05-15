package com.healthdomian.service;

import java.util.List;



import com.healthdomian.request.SearchRequest;
import com.healthdomian.response.PlainResponse;


public interface InsurancePlanService {

	public List<PlainResponse>  searchPlan(SearchRequest searchRequest);
	
	public List<String>  getUniquePlanNames();
	
	public List<String>  getUniquePlanStatus();
}
