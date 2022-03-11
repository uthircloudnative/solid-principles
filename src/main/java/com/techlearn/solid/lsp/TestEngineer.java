package com.techlearn.solid.lsp;
/**
 * @author Uthiraraj Saminathan
 *
 */
public class TestEngineer implements IEngineer, ITestEngineer {
	
	@Override
	public void logHours(float hours) {
		System.out.println("logHours Implementation");	
		
	}

	@Override
	public void submitPerformanceGoals() {
		System.out.println("submitPerformanceGoals Implementation");	
		
	}
	@Override
	public void shareTestScripts(String assignee) {
		System.out.println("shareTestScripts Implementation");		
	}
	
}
