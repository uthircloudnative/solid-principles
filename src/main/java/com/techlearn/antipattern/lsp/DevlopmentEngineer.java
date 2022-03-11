package com.techlearn.antipattern.lsp;
/**
 * @author Uthiraraj Saminathan
 *
 */
public class DevlopmentEngineer extends Engineer{

	@Override
	public void logHours(float hours) {
		System.out.println("Log Hours");
	}

	@Override
	public void submitPerformanceGoals() {
		System.out.println("Submit Performance Goals");
		
	}

	@Override
	public void reviewCode() {
		System.out.println("Review Code");
		
	}

	@Override
	public void shareTestScripts(String assignee) {
		//Developer don't need to shareTest Scripts.
		 throw new UnsupportedOperationException();
		
	}

	@Override
	public void givePerformanceReview(String assignee) {
		//PerfEngineer don't need to givePerformanceReview.
		 throw new UnsupportedOperationException();
	}
	
	

}
