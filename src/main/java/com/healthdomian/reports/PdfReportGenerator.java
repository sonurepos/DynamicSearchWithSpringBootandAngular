package com.healthdomian.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.healthdomian.response.PlainResponse;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfReportGenerator {
	
	public void export(List<PlainResponse> plans , HttpServletResponse response) throws DocumentException, IOException {
		
		Document docuemnt=new Document(PageSize.A4);
		PdfWriter.getInstance(docuemnt, response.getOutputStream());
		docuemnt.open();
		
		Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		
		Paragraph p=new Paragraph("List of Plans" , font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		
		docuemnt.add(p);
		
		//pdf table
		PdfPTable table= new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[]{1.5f , 1.5f , 2.0f , 3.0f , 2.0f});
		table.setSpacingBefore(10);
		
		//pdf cell
		PdfPCell cell= new PdfPCell();
		cell.setBackgroundColor(Color.YELLOW);
		cell.setPadding(5);
		
		Font font1=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font1.setColor(Color.BLACK);
		
		cell.setPhrase(new Phrase("Plain ID", font1));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Plan Name" , font1));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Plan Status", font1));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Holder Name" , font1));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Benefit Amount" , font1));
		table.addCell(cell);
		
		for(PlainResponse plan : plans) {
			table.addCell(String.valueOf(plan.getPlanId()));
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			table.addCell(plan.getHolderName());
			table.addCell(plan.getBenefitAmount());
		}
		docuemnt.add(table);
		docuemnt.close();
		
	}

}
