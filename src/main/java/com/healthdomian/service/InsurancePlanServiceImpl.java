package com.healthdomian.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.healthdomian.entity.InsurancePlan;
import com.healthdomian.repo.InsurancePlanRepository;
import com.healthdomian.request.SearchRequest;
import com.healthdomian.response.PlainResponse;

@Service
public class InsurancePlanServiceImpl implements InsurancePlanService {

	@Autowired
	private InsurancePlanRepository insuranceRepo;

	@Override
	public List<PlainResponse> searchPlan(SearchRequest searchRequest) {

		InsurancePlan entity = new InsurancePlan();
		
//Note : user has 4 choices 1> if user select plan Name 2>if user select plan status 3> if user select both plan name and plan status 4> if user search with select any dropdown
//for 1st choice 1st if cndt , for 2nd chioce 2nd if cndtn , for 3rd choice both if cndtn , for 4th choice null value pass as entity so no filter all record will show

//if user select only plan Name  as dropdown		
		if (searchRequest != null && searchRequest.getPlanName() != null && !searchRequest.getPlanName().equals("")) {
			entity.setPlanName(searchRequest.getPlanName());
		}

 //if user select only plan status as dropdown
		if(searchRequest!=null  && searchRequest.getPlanStatus()!=null && !searchRequest.getPlanStatus().equals("")) {
			entity.setPlanStatus(searchRequest.getPlanStatus());
		}
		
//filter entity object as per chocie
		Example<InsurancePlan> example = Example.of(entity);
		List<InsurancePlan> listOfInsurancePlanEntity = insuranceRepo.findAll(example);
		
//converting  List of InsurancePlan entity class object to PlanResponse binding class object so throgh forEach we are iterating and BeanUtils.propertOf() we are copying to PlanResponse and keeping in ArrayList		
	
	List<PlainResponse> arrListPlanRespone=new ArrayList<>();
	
	for(InsurancePlan insurancePlanEntity : listOfInsurancePlanEntity) {
		
		PlainResponse planResponseEntity = new PlainResponse();
		
		BeanUtils.copyProperties(insurancePlanEntity, planResponseEntity);
		
		arrListPlanRespone.add(planResponseEntity);
	}
	return arrListPlanRespone;
		
	}

	@Override
	public List<String> getUniquePlanNames() {

		return insuranceRepo.getUniquePlanNamesCustomerMethod();
	}

	@Override
	public List<String> getUniquePlanStatus() {

		return insuranceRepo.getUniquePlanStatusCustomMethod();
	}

}
