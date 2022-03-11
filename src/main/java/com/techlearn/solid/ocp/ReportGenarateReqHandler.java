package com.techlearn.solid.ocp;

import java.time.LocalDate;
import java.util.Arrays;

import com.techlearn.antipattern.srm.User;

/**
 * @author Uthiraraj Saminathan
 *
 */
public class ReportGenarateReqHandler {
	
	public static void main(String[] args) {
		
		ReportGenaratorFactory reportFactory = new ReportGenaratorFactory();
		
		ReportGenerator pdfReportGenarator = reportFactory.generateReport("PDF");
		
		pdfReportGenarator.generateReport(Arrays.asList(new User("TestF","TestLast",
				                                                 LocalDate.now(),"testocp@gmail.com")));
		
	}
}
