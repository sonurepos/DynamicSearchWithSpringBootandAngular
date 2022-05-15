package com.healthdomian.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthdomian.reports.ExcelReportGenerator;
import com.healthdomian.reports.PdfReportGenerator;
import com.healthdomian.request.SearchRequest;
import com.healthdomian.response.PlainResponse;
import com.healthdomian.service.InsurancePlanService;

@RestController
@CrossOrigin
public class InsurancePlanRestController {

	@Autowired
	private InsurancePlanService insurancePlanService;
	
//	when user click plan name dropdown
	@GetMapping("/planames")
	public ResponseEntity<List<String>> getPlanNamesRestController(){
		List<String> uniquePlanNamesList = insurancePlanService.getUniquePlanNames();
		return new ResponseEntity<>(uniquePlanNamesList , HttpStatus.OK);
	}
	
//	when user click plan status dropdown
	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> getStatusNameRestController(){
		List<String> uniquePlanStatusList = insurancePlanService.getUniquePlanStatus();
		return new ResponseEntity<>(uniquePlanStatusList , HttpStatus.OK);
	}
//	when user click search button (user can search with plan name or plan status or without clikinmg both
	@PostMapping("/searchPlans")
	public ResponseEntity<List<PlainResponse>> searchPlans(@RequestBody SearchRequest searchRequest){
		List<PlainResponse> listOfPlans = insurancePlanService.searchPlan(searchRequest);
		return new ResponseEntity<>(listOfPlans , HttpStatus.OK);
	}
	
//	when user click excel link then  it will download excel with all data
	@GetMapping("/generateExcel")
	public void generateExcel(HttpServletResponse response) throws IOException {
		
//	setting content type and header to download excel
		response.setContentType("application/octet-stream");
		
		String headerKey="Content-Disposition";
		String headervalue="attachment; filename=Plans.xlsx";
		response.setHeader(headerKey, headervalue);
		
		
//	calling service class to get all data and pasing data and response to Excel 
		
		List<PlainResponse> plans= insurancePlanService.searchPlan(null); //passing null bcz we want all records 
		
		ExcelReportGenerator excelReportGenerator=new ExcelReportGenerator();
		excelReportGenerator.export(plans, response);
	}
	
//	when user click excel link then  it will download excel with all data
	@GetMapping("/generatePdf")
	public void generatePdf(HttpServletResponse response) throws IOException {
		
//	setting content type and header to download excel
		response.setContentType("application/pdf");
		
		String headerKey="Content-Disposition";
		String headervalue="attachment; filename=Plans.pdf";
		response.setHeader(headerKey, headervalue);
		
		
//	calling service class to get all data and pasing data and response to Excel 
		
		List<PlainResponse> plans= insurancePlanService.searchPlan(null); //passing null bcz we want all records 
		
		PdfReportGenerator pdfReportGenerator=new PdfReportGenerator();
		pdfReportGenerator.export(plans, response);
	}
}
