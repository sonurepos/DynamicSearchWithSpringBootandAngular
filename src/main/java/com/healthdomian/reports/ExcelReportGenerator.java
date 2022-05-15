package com.healthdomian.reports;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.helpers.XSSFColumnShifter;

import com.healthdomian.response.PlainResponse;

public class ExcelReportGenerator {

	public void export(List<PlainResponse> listOfPlans , HttpServletResponse response) throws IOException {
		
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("Insurance Plans");
		
//	first row	for header
		XSSFRow headerRow=sheet.createRow(0); 
		
// 5 cloums header  as per requirement
		headerRow.createCell(0).setCellValue("Plan ID");
		headerRow.createCell(1).setCellValue("Holder Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Benefit Amount");
		
//	from 2nd row , data will populate with respective column
		
		for(int i =0; i < listOfPlans.size(); i++) {
			
			XSSFRow dataRow=sheet.createRow(i + 1); // it will start from 1
			
			PlainResponse plan=listOfPlans.get(i);
			
			dataRow.createCell(0).setCellValue(plan.getPlanId());
			dataRow.createCell(1).setCellValue(plan.getHolderName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());
			dataRow.createCell(4).setCellValue(plan.getBenefitAmount());
			
		}
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		
		workbook.close();
		outputStream.close();
		
	}
}
