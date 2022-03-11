package com.techlearn.solid.ocp;

import java.util.List;

import com.techlearn.antipattern.srm.User;

/**
 * 
 * 
 * @author Uthiraraj Saminathan
 *
 */
public class ReportGenaratorFactory {

	
	public ReportGenerator generateReport(String formatType) {
		
		if("PDF".equals(formatType)) {
			return new PDFReportGenerator();
		}else if("CSV".equals(formatType)){
			return new CSVReportGenarator();
		}else {
			return new TextReportGenarator();
		}
	}
}
