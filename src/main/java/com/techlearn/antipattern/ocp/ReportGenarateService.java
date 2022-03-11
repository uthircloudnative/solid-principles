package com.techlearn.antipattern.ocp;

import java.util.List;

import com.techlearn.antipattern.srm.User;

/**
 * @author Uthiraraj Saminathan
 *
 */
public class ReportGenarateService {
	
	public void generateReport(List<User> users, String reportFormat) {
		
		if("PDF".equals(reportFormat)) {
			//Create PDF Report and Store
		}else if("CSV".equals(reportFormat)) {
			//Create CSV Report and Store.
		}else {
			//Create txt Report and Store
		}
	}
}
